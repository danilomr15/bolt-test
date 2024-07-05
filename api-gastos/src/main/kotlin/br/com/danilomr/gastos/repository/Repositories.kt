package br.com.danilomr.gastos.repository

import br.com.danilomr.gastos.entity.Gasto
import br.com.danilomr.gastos.entity.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

interface GastoRepository : CrudRepository<Gasto, Long> {

    fun findByDataBetween(dataInicial: LocalDate, dataFinal: LocalDate) : List<Gasto>

    fun findAll(pageable: Pageable) : Page<Gasto>
}

interface TagRepository : CrudRepository<Tag, Long> {

    fun findByNomeTag(nomeTag: String) : Optional<Tag>

    fun findAll(sort: Sort) : List<Tag>

    @Transactional
    @Modifying
    @Query("DELETE FROM GASTOS_TAGS gt WHERE TAG_ID = :tagId", nativeQuery = true)
    fun deleteTagAssociations(tagId: Long)
}