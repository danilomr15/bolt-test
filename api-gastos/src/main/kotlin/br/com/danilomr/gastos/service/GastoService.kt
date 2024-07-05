package br.com.danilomr.gastos.service

import br.com.danilomr.gastos.dto.RelatorioDiarioDto
import br.com.danilomr.gastos.dto.RelatorioDto
import br.com.danilomr.gastos.entity.Gasto
import br.com.danilomr.gastos.entity.Tag
import br.com.danilomr.gastos.exception.DataInvalidaException
import br.com.danilomr.gastos.exception.GastoNaoEncontradoException
import br.com.danilomr.gastos.repository.GastoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class GastoService(private val gastoRepository: GastoRepository, private val tagService: TagService) {

    var tamanhoPaginaPadrao = 10

    fun listarTodos(pagina: Int) : Page<Gasto> {

        var ordem = Sort.by(Sort.Direction.ASC, "data")
        val paginador = PageRequest.of(pagina, tamanhoPaginaPadrao, ordem)
        return gastoRepository.findAll(paginador)
    }

    fun novo(gasto: Gasto) : Gasto {

        val tags = tagService.validarTags(gasto.tags)
        gasto.tags = tags
        return gastoRepository.save(gasto)
    }

    fun atualizar(id: Long, gasto: Gasto) : Gasto {

        val tags = tagService.validarTags(gasto.tags)
        val gastoEncontrado = buscarPorId(id)
        gasto.id = gastoEncontrado.id
        gasto.tags = tags
        return gastoRepository.save(gasto)
    }

    fun excluir(id: Long) {

        val gastoEncontrado = buscarPorId(id)
        gastoEncontrado.tags = ArrayList()
        gastoRepository.save(gastoEncontrado)
        gastoRepository.delete(gastoEncontrado)
    }

    fun buscarPorId(id: Long) : Gasto {

        val gastoEncontrado = gastoRepository.findById(id)
        if(gastoEncontrado.isEmpty) {
            throw GastoNaoEncontradoException()
        }
        return gastoEncontrado.get()
    }

    fun relatorio(dataInicial: LocalDate, dataFinal: LocalDate) : RelatorioDto {

        validarRangeDeDatas(dataInicial, dataFinal)
        val gastosDiarios = ArrayList<RelatorioDiarioDto>()
        val gastos = gastoRepository.findByDataBetween(dataInicial, dataFinal)
        if (gastos.isEmpty()) {
            return RelatorioDto(dataInicial, dataFinal, gastosDiarios, null)
        }

        var dataAtual = dataInicial
        while(dataAtual.compareTo(dataFinal) <= 0) {

            val gastosFiltro = gastos.filter { it.data == dataAtual }
            if(gastosFiltro.isEmpty()) {
                dataAtual = dataAtual.plusDays(1)
                continue
            }

            val total = gastosFiltro.sumOf { it.valor }
            val registros = gastosFiltro.size
            val mediaDiaria = total.divide(BigDecimal(registros))
            val gastosDto = gastosFiltro.map { it.toDto() }

            gastosDiarios.add(RelatorioDiarioDto(dataAtual, gastosDto, mediaDiaria))
            dataAtual = dataAtual.plusDays(1)
        }

        val dataMaiorGasto = gastosDiarios.maxBy { it.mediaDiaria }

        return RelatorioDto(dataInicial, dataFinal, gastosDiarios, dataMaiorGasto.data)
    }

    private fun validarRangeDeDatas(dataInicial: LocalDate, dataFinal: LocalDate) {

        if(dataInicial.compareTo(dataFinal) > 0) {
            throw DataInvalidaException()
        }
    }
}