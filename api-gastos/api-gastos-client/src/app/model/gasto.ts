import { Tag } from "./tag"

export class Gasto {
    id: number
    nomePessoa: string
    descricao: string
    data: string
    valor: number
    tags: Tag[]

    constructor(id: number, nomePessoa: string, descricao: string, data: string, valor: number, tags: Tag[]) {
        this.id = id
        this.nomePessoa = nomePessoa
        this.descricao = descricao
        this.data = data
        this.valor = valor
        this.tags = tags
    }
}

export class ListarGasto {
    totalPages: number
    content: Gasto[]
    first: boolean
    last: boolean
    pageable: Paginacao

    constructor(totalPages: number, content: Gasto[], first: boolean, last: boolean, pageable: Paginacao) {
        this.totalPages = totalPages
        this.content = content
        this.first = first
        this.last = last
        this.pageable = pageable
    }
}

export class Paginacao {
    pageNumber: number

    constructor(pageNumber: number) {
        this.pageNumber = pageNumber
    }
}