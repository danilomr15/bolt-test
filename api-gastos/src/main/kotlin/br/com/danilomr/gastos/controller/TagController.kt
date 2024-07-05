package br.com.danilomr.gastos.controller

import br.com.danilomr.gastos.dto.TagDto
import br.com.danilomr.gastos.service.TagService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = ["http://localhost:4200"])
class TagController(private val tagService: TagService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listarTodos() : List<TagDto> {

        val response = tagService.listarTodos();
        return response.map { it.toDto() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun novo(@Valid @RequestBody request: TagDto) : TagDto {

        val response = tagService.novo(request.toEntity())
        return response.toDto()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun excluir(@PathVariable id: Long) {

        tagService.excluir(id)
    }
}