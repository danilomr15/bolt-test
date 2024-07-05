package br.com.danilomr.gastos.exception

import org.springframework.http.HttpStatus

class GastoNaoEncontradoException: SystemException(MENSAGEM_PADRAO, HttpStatus.NOT_FOUND.value()) {

    companion object {
        const val MENSAGEM_PADRAO = "Gasto não encontrado"
    }
}