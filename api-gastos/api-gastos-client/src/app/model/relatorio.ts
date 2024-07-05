import { Gasto } from "./gasto"

export class Relatorio {
    dataInicial: string
    dataFinal: string
    dataMaiorGasto: string
    mediasDiarias: RelatorioDiario[]

    constructor(dataInicial: string, dataFinal: string, dataMaiorGasto: string, mediasDiarias: RelatorioDiario[]) {
        this.dataInicial = dataInicial
        this.dataFinal = dataFinal
        this.dataMaiorGasto = dataMaiorGasto
        this.mediasDiarias = mediasDiarias
    }
}

export class RelatorioDiario {

    data: string
    mediaDiaria: Number
    gastos: Gasto[]

    constructor(data: string, mediaDiaria: Number, gastos: Gasto[]) {
        this.data = data
        this.mediaDiaria = mediaDiaria
        this.gastos = gastos
    }
}