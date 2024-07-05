package br.com.danilomr.gastos.exception

import br.com.danilomr.gastos.dto.ErroDto
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime


@ControllerAdvice
class GlobalExceptionHandler {

    companion object {
        const val MENSAGEM_PADRAO = "Erro interno"
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(ex: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErroDto> {

        val erroPrincipal = ex.bindingResult.allErrors.stream()
                .filter { it != null }
                .findFirst()
                .orElse(null)

        val message = erroPrincipal?.defaultMessage ?: MENSAGEM_PADRAO
        return response(HttpStatus.BAD_REQUEST.value(), message, request.method, request.requestURI)

    }

    @ExceptionHandler(SystemException::class)
    fun handle(ex: SystemException, request: HttpServletRequest): ResponseEntity<ErroDto> {

        return response(ex.codigoStatus, ex.message ?: "", request.method, request.requestURI)
    }

    @ExceptionHandler(Exception::class)
    fun handle(ex: Exception, request: HttpServletRequest): ResponseEntity<ErroDto> {

        val message = if (ex.message.isNullOrBlank()) MENSAGEM_PADRAO else ex.message!!
        return response(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, request.requestURI, request.method)
    }

    private fun response(
            codigoStatus: Int,
            mensagem: String,
            endpoint: String,
            metodo: String
    ): ResponseEntity<ErroDto> {

        return ResponseEntity
                .status(codigoStatus)
                .body(ErroDto(mensagem, LocalDateTime.now(), endpoint, metodo, codigoStatus))
    }
}