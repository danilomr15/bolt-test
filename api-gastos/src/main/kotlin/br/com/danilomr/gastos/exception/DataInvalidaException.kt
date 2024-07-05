package br.com.danilomr.gastos.exception

import org.springframework.http.HttpStatus

class DataInvalidaException: SystemException(MENSAGEM_PADRAO, HttpStatus.BAD_REQUEST.value()) {

    companion object {
        const val MENSAGEM_PADRAO = "A data inicial não pode ser maior que a data final"
    }
}