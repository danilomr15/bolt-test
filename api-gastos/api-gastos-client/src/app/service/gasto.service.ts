import { Injectable } from '@angular/core';
import { Observable, catchError, retry, throwError } from "rxjs";
import { Gasto, ListarGasto } from "../model/gasto"
import { Relatorio, RelatorioDiario } from "../model/relatorio";
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";

@Injectable({ 
    providedIn: 'root' 
})
export class GastoService {

    dataInicial!: string;
    dataFinal!: string;

    url = 'http://localhost:8080/api/gastos'

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    constructor(private httpClient: HttpClient) {}

    listar(pagina: number): Observable<ListarGasto> {
        return this.httpClient.get<ListarGasto>(this.url + '/listar?pagina=' + pagina)
    }

    salvar(gasto: Gasto) {
        return this.httpClient.post<Gasto>(this.url, JSON.stringify(gasto), this.httpOptions)
        .pipe(
            catchError(this.handleError)
          )
    }
    
    atualizar(id: number, gasto: Gasto) {
        return this.httpClient.put<Gasto>(this.url + '/' + id, JSON.stringify(gasto), this.httpOptions)
    }

    excluir(id: number) {
        return this.httpClient.delete<Gasto>(this.url + '/' + id, this.httpOptions)
    }
    
    gerarRelatorio(dataInicial: string, dataFinal: string): Observable<Relatorio> {

        return this.httpClient.get<Relatorio>(this.url + '/relatorio?dataInicial=' + dataInicial + '&dataFinal=' + dataFinal)
    }

    handleError(error: HttpErrorResponse) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
          // Erro ocorreu no lado do client
          errorMessage = error.error.message;
        } else {
          // Erro ocorreu no lado do servidor
          errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
        }
        console.log(errorMessage);

        const err = new Error(errorMessage); 
        return throwError(() => err);
      };
}