package br.com.danilomr.gastos.exception

open class SystemException(mensagem: String, val codigoStatus: Int) : RuntimeException(mensagem) {

}