package br.com.danilomr.gastos.controller

import br.com.danilomr.gastos.dto.GastoDto
import br.com.danilomr.gastos.dto.RelatorioDto
import br.com.danilomr.gastos.service.GastoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/gastos")
@CrossOrigin(origins = ["http://localhost:4200"])
class GastoController(private val gastosService: GastoService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun novo(@Valid @RequestBody request: GastoDto) : GastoDto {

        val response = gastosService.novo(request.toEntity())
        return response.toDto()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun atualizar(@PathVariable id: Long, @Valid @RequestBody request: GastoDto) : GastoDto {

        val response = gastosService.atualizar(id, request.toEntity())
        return response.toDto()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun excluir(@PathVariable id: Long) {

        gastosService.excluir(id)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscarPorId(@PathVariable id: Long) : GastoDto {

        val response = gastosService.buscarPorId(id)
        return response.toDto()
    }

    @GetMapping("/relatorio")
    @ResponseStatus(HttpStatus.OK)
    fun relatorio(@RequestParam(name = "dataInicial", required = true) dataInicial: LocalDate,
                  @RequestParam(name = "dataFinal", required = true) dataFinal: LocalDate) : RelatorioDto {

        return gastosService.relatorio(dataInicial, dataFinal)
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    fun listarTodos(@RequestParam(name = "pagina", required = true) pagina: Int) : Page<GastoDto> {

        val response = gastosService.listarTodos(pagina)
        return response.map { it.toDto() }
    }
}