package br.com.danilomr.gastos.exception

import org.springframework.http.HttpStatus

class TagExistenteException: SystemException(MENSAGEM_PADRAO, HttpStatus.CONFLICT.value()) {

    companion object {
        const val MENSAGEM_PADRAO = "A Tag com o nome informado já existe"
    }
}