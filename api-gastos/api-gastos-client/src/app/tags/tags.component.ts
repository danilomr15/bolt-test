import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Tag } from '../model/tag';
import { TagService } from '../service/tag.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tags',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './tags.component.html',
  styleUrl: './tags.component.css',
  providers: []
})
export class TagsComponent implements OnInit{

  tags: Tag[] = [];
  tagInput!: string;
  tagSelecionada: Tag = {} as Tag

  constructor(private tagService: TagService) {}

  ngOnInit(): void {

    this.listarTags()
  }

  listarTags() {
    this.tagService.listar().subscribe((tags: Tag[]) => {
      this.tags = tags
    })
  }
 
  selecionarTag(tag: Tag) {

    this.tagSelecionada = {...tag}
  }

  confirmarExclusao() {
    this.tagService.excluir(this.tagSelecionada.id as number).subscribe(() => {
      this.listarTags()
    })
  }

  adicionarTag() {

    const tag: Tag = {nomeTag: this.tagInput}

    this.tagService.salvar(tag).subscribe(() => {
      this.listarTags()
    })
  }
}
