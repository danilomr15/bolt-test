package br.com.danilomr.gastos.entity

import br.com.danilomr.gastos.dto.GastoDto
import br.com.danilomr.gastos.dto.TagDto
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "GASTOS")
class Gasto (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gastos_generator")
        @SequenceGenerator(name = "gastos_generator", sequenceName = "gastos_seq", allocationSize = 1)
        @Column(name = "ID") var id: Long? = null,
        @Column(name = "NOME") var nomePessoa: String,
        @Column(name = "DESCRICAO") var descricao: String,
        @Column(name = "DATA") var data: LocalDate,
        @Column(name = "VALOR") var valor: BigDecimal,
        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH], fetch = FetchType.LAZY)
        @JoinTable(name = "GASTOS_TAGS",
                joinColumns = [JoinColumn(name = "GASTO_ID")],
                inverseJoinColumns = [JoinColumn(name = "TAG_ID")])
        var tags: List<Tag>) {

    fun toDto() : GastoDto {
        val tags = this.tags.map { tag ->
            tag.toDto()
        }
        return GastoDto(this.id, this.nomePessoa, this.descricao, this.data, valor, tags)
    }
}

@Entity
@Table(name = "TAGS")
class Tag(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tags_generator")
        @SequenceGenerator(name = "tags_generator", sequenceName = "tags_seq", allocationSize = 1)
        @Column(name = "ID") var id: Long? = null,
        @Column(name = "NOME") var nomeTag: String) {

    fun toDto() : TagDto {
        return TagDto(this.id, this.nomeTag)
    }
}



