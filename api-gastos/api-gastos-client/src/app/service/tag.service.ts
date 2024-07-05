import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { Tag } from "../model/tag";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({ 
    providedIn: 'root' 
})
export class TagService {

    url = 'http://localhost:8080/api/tags'

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    constructor(private httpClient: HttpClient) {}

    listar(): Observable<Tag[]> {
        return this.httpClient.get<Tag[]>(this.url)
    }

    salvar(tag: Tag) {
        return this.httpClient.post<Tag>(this.url, JSON.stringify(tag), this.httpOptions)
    }

    excluir(id: number) {
        return this.httpClient.delete<Tag>(this.url + '/' + id, this.httpOptions)
    }
}