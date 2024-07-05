import { Component,  OnInit } from '@angular/core';
import { GastoService } from '../service/gasto.service';
import { Relatorio } from '../model/relatorio';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'relatorio',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './relatorio.component.html',
  styleUrl: './relatorio.component.css',
  providers: [GastoService]
})
export class RelatorioComponent implements OnInit {


  dataInicial!: string;
  dataFinal!: string;

  relatorio: Relatorio = {} as Relatorio;

  constructor(private gastoService: GastoService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.dataInicial = params['dataInicial'];
      this.dataFinal = params['dataFinal'];

      this.gastoService.gerarRelatorio(this.dataInicial, this.dataFinal).subscribe((relatorio: Relatorio) => {
        this.relatorio = {...relatorio}
      })
    });
  }
}
