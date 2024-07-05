import { Component, OnInit } from '@angular/core';
import { Gasto, ListarGasto } from '../model/gasto';
import { Tag } from '../model/tag';
import { GastoService } from '../service/gasto.service';
import { TagService } from '../service/tag.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-gastos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './gastos.component.html',
  styleUrl: './gastos.component.css',
  providers: []
})
export class GastosComponent implements OnInit{

  listaGastos!: ListarGasto;
  gastos: Gasto[] = [];
  tags: Tag[] = [];
  dataInicial!: string;
  dataFinal!: string;
  gastoSelecionado: Gasto = <Gasto>{};
  tagsSelecionadas: string[] = [];
  paginacao: number[] = [];
  paginaAtual!: number;
  desabilitarBotaoAnterior!: boolean;
  desabilitarBotaoProximo!: boolean;

  constructor(private gastoService: GastoService, private tagService: TagService, private router: Router) {}

  ngOnInit(): void {
    this.listarGastos(0);
    this.tagService.listar().subscribe((tags: Tag[]) => {
      this.tags = tags;
    })
  }

  listarGastos(pagina: number) {

    try {
      this.gastoService.listar(pagina).subscribe((listaGastos: ListarGasto) => {
        this.listaGastos = listaGastos;
        this.configurarPaginacao();
      })
    } catch (ex: unknown) {
      console.log(ex)
    } 
  }

  salvarGasto() {
      this.tagsSelecionadas.forEach((tag => {
        this.addTag(tag)
      }))

    this.gastoService.salvar(this.gastoSelecionado).subscribe(() => {
      this.listarGastos(0);
    })
  }

  atualizarGasto() {
    this.gastoService.atualizar(this.gastoSelecionado.id, this.gastoSelecionado).subscribe(() => {
      this.listarGastos(0)
    })
  }

  confirmarExclusao() {
    this.gastoService.excluir(this.gastoSelecionado.id).subscribe(() => {
      this.listarGastos(0)
    })
  }

  selecionarGasto(gasto: Gasto) {
    this.gastoSelecionado = {...gasto}
  }

  gerarRelatorio() {
    this.router.navigate(['/relatorio'], 
      { queryParams: { dataInicial: this.dataInicial, dataFinal: this.dataFinal } });
  }

  resetarGasto() {
    this.gastoSelecionado = {} as Gasto;
    this.gastoSelecionado.tags = [];
    this.tagsSelecionadas = [];
  }

  addTag(nomeTag: string) {
    this.gastoSelecionado.tags.push({nomeTag: nomeTag})
  }

  configurarPaginacao() {

    this.paginacao = [];

    if (this.listaGastos?.pageable?.pageNumber == null) {
      this.paginaAtual = 0;
    } else {
      this.paginaAtual = this.listaGastos.pageable.pageNumber
    }

    if (this.listaGastos?.totalPages == null) {
      this.paginacao.push(1)
    } else {
      this.paginacao = Array(this.listaGastos.totalPages).fill(0).map((x, i)=> i + 1)
    }

    if (this.listaGastos?.first == null || this.listaGastos?.first) {
      this.desabilitarBotaoAnterior = true;
    } else {
      this.desabilitarBotaoAnterior = false;
    }

    if (this.listaGastos?.last == null || this.listaGastos?.last) {
      this.desabilitarBotaoProximo = true;
    } else {
      this.desabilitarBotaoProximo = false;
    }
  }

  proximaPagina() {
    this.paginaAtual = this.paginaAtual + 1;
    this.listarGastos(this.paginaAtual)
  }

  paginaAnterior() {
    this.paginaAtual = this.paginaAtual - 1;
    this.listarGastos(this.paginaAtual)
  }
}
