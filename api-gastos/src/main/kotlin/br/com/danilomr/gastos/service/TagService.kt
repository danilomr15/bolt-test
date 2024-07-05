package br.com.danilomr.gastos.service

import br.com.danilomr.gastos.entity.Tag
import br.com.danilomr.gastos.exception.TagExistenteException
import br.com.danilomr.gastos.exception.TagNaoEncontradaException
import br.com.danilomr.gastos.repository.TagRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TagService(private val tagRepository: TagRepository) {

    fun listarTodos() : List<Tag> {

        return tagRepository.findAll(Sort.by(Sort.Direction.ASC, "nomeTag"))
    }

    fun novo(tag: Tag) : Tag {

        val tagEncontrada = tagRepository.findByNomeTag(tag.nomeTag)
        if(tagEncontrada.isPresent) {
            throw TagExistenteException()
        }
        tag.nomeTag = tag.nomeTag.uppercase()
        return tagRepository.save(tag)
    }

    @Transactional
    fun excluir(tagId: Long) {

        val tagEncontrada = tagRepository.findById(tagId)
        if(tagEncontrada.isEmpty) {
            throw TagNaoEncontradaException()
        }

        tagRepository.deleteTagAssociations(tagId)
        tagRepository.delete(tagEncontrada.get())
    }

    fun validarTags(tags: List<Tag>) : List<Tag> {

        if(tags.isEmpty()) {
            return ArrayList()
        }

        val tagsEncontradas = ArrayList<Tag>()
        tags.forEach{
            val response = buscarPorNome(it.nomeTag)
            tagsEncontradas.add(response)
        }
        return tagsEncontradas
    }

    private fun buscarPorNome(nomeTag: String) : Tag {

        val tag = tagRepository.findByNomeTag(nomeTag)
        if (tag.isEmpty) {
            throw TagNaoEncontradaException()
        }
        return tag.get()
    }
}