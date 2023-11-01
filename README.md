# Custumer Service

Projeto da prova pratica de desenvolvimento Java finalizado.

### Requisitos

1. JDK 8
1. Maven 3

### Tecnologias utilizadas
1. Spring Boot 3
1. [JPA](https://spring.io/guides/gs/accessing-data-jpa/)
1. Swagger ([Springdoc](https://springdoc.org/)) 
1. [ModelMapper](https://modelmapper.org/)
1. [Lombok](https://projectlombok.org/)
1. [H2](https://www.h2database.com/html/main.html)
 
### Rodando

1. Clone o projeto: `https://github.com/leonardohenrique/practical-test.git`
1. Entre na pasta `tokio-test` e execute: `mvn spring-boot:run`
1. Acesse: `http://localhost:8080/swagger-ui/index.html`

### Banco de dados

Esta aplicação usa o banco de dados H2, uma base de dados em memória e não será necessário fazer configurações de conexão com um banco externo.

O console do banco esta disponível em: http://localhost:8080/h2-console:

Ao acessar essa URL, você verá a página de login do console do H2. 
Utilize as seguinte configurações para fazer o login e ter acesso:

- JDBC URL: jdbc:h2:mem:testdb
- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password:

### Springdoc

A documentação da API gerada pelo Springdoc estará disponível em `http://localhost:8080/swagger-ui.html`.

### Estrutura do projeto

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.example.api
│   │   │   │   ├── domain
│   │   │   │   │   ├── [Entidades JPA]
│   │   │   │   ├── repository
│   │   │   │   │   ├── [Repositórios JPA]
│   │   │   │   ├── service
│   │   │   │   │   ├── [Serviços da aplicação]
│   │   │   │   ├── ├── dto
│   │   │   │   │   ├── ├── [DTOs da aplicação]
│   │   │   │   ├── web
│   │   │   │   │   ├── rest
│   │   │   │   │   │   ├── [Controllers da aplicação]
│   │   │   │   │   │   ├── error
│   │   │   │   │   │   │   ├── [Tratamento de erros]
│   │   ├── resources
│   │   │   ├── application.properties
│   ├── test
│   │   ├── java
│   │   │   ├── [pacote-do-projeto]
│   │   │   │   ├── [Testes da aplicação]
├── pom.xml
└── README.md
```
