package br.com.danilomr.gastos.dto

import br.com.danilomr.gastos.entity.Gasto
import br.com.danilomr.gastos.entity.Tag
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

class GastoDto(
        var id: Long?,

        @field:NotBlank(message = "O campo 'nomePessoa' é obrigatório")
        var nomePessoa: String,

        @field:NotBlank(message = "O campo 'descricao' é obrigatório")
        var descricao: String,

        @field:NotNull(message = "O campo 'data' é obrigatório")
        var data: LocalDate,

        @field:NotNull(message = "O campo 'valor' é obrigatório")
        var valor: BigDecimal,

        @OneToMany var tags: List<TagDto>) {

    fun toEntity() : Gasto {
        val tags = this.tags.map { tagDto ->
            tagDto.toEntity()
        }
        return Gasto(null, this.nomePessoa, this.descricao, this.data, valor, tags)
    }
}

class TagDto(
        var id: Long?,

        @field:NotBlank(message = "O campo 'nomeTag' é obrigatório")
        var nomeTag: String) {

    fun toEntity() : Tag {
        return Tag(null, this.nomeTag)
    }
 }

class RelatorioDto(
        var dataInicial: LocalDate,
        var dataFinal: LocalDate,
        var mediasDiarias: List<RelatorioDiarioDto>,
        var dataMaiorGasto: LocalDate?
)

class RelatorioDiarioDto(
        var data: LocalDate,
        var gastos: List<GastoDto>,
        var mediaDiaria: BigDecimal
)

class ErroDto(
        var mensagem: String,
        var dataHora: LocalDateTime,
        var endpoint: String,
        var metodo: String,
        var codigoStatus: Int
)