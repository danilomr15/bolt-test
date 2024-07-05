package br.com.danilomr.gastos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiGastosApplication

fun main(args: Array<String>) {
	runApplication<ApiGastosApplication>(*args)
}
