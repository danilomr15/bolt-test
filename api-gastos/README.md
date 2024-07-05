# API de Gastos
## O desafio

### 1) Criar uma API RESTful para gerenciar gastos de uma pessoa.
     
**Modelo de dados:**
* Gasto:
  - Id gasto
  - Nome da pessoa
  - Descrição
  - data/hora
  - Valor
  - Tags

  
* Tag:
  * Id tag
  * Nome tag

**Funcionalidades desejadas:**

* Criar, atualizar e remover um gasto.
* Detalhar um gasto por id.
* Listar todos os gastos.
* Relatório 
  * Dado um período (data inicial e data final) retornar o gasto médio por dia e o dia
  com maior gasto.


**Considerações:**
* Persistir em um banco de dados relacional.


### 2) Criar uma collection no postman para documentar a api


### 3) Desafio extra. 
Criar um projeto frontend (angular, react, vue, qualquer um que desejar)
   para consumir a API criada no passo 1.
* Tela de cadastro de um gasto
* Tela de visualização de todos os gastos
* Tela de relátorio.

## Tecnologias utilizadas

* Java 17
* Kotlin
* Spring Boot 3
* JPA
* Angular 18
* Bootstrap 5.2
* Docker

## Pré requisitos para rodar a aplicação

* Java
* Maven
* Docker
* Docker compose

## Rodando a aplicação

No diretório raiz do projeto (api-gastos) onde encontra-se o arquivo pom.xml, 
precisamos gerar o executável da nossa aplicação através do seguinte comando:

``` mvn clean install ```

No mesmo diretório, agora com o executável do backend já pronto, precisamos 
montar as imagens necessárias para compor o container, e para isso precisamos
executar o seguinte comando:

``` docker-compose build ```

Finalmente com o executável em mãos e também as imagens necessárias, podemos
criar o nosso container através do comando:

``` docker-compose up -d ```

## Considerações finais

Todas as funcionalidades disponíveis são chamadas diretamente do frontend
para o backend. Para melhor explicar como esses serviços funcionam, uma pasta
foi criada no projeto (/api-gastos/postman) contendo a collection com as chamadas
e um exemplo de resposta de cada um dos serviços.