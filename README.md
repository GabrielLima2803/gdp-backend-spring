# Gerenciamento de Planos API

Este projeto é uma API para gerenciamento de planos, desenvolvida utilizando **Spring Boot**, **Spring Security**, **RabbitMQ**, **JWT** e **Swagger**. O software foi implementado seguindo os princípios do **Domain Driven Design (DDD)**, proporcionando uma arquitetura modular, organizada e de fácil manutenção.

## Sumário

- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura do Projeto](#arquitetura-do-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
    - [Models / Entities](#models--entities)
    - [DTO](#dto)
    - [Mappers](#mappers)
    - [Use Cases](#use-cases)
    - [Controllers](#controllers)
    - [Repositories](#repositories)
- [Configurações](#configurações)
    - [Spring Security e JWT](#spring-security-e-jwt)
    - [RabbitMQ](#rabbitmq)
    - [Swagger](#swagger)

## Visão Geral

A **Gerenciamento de Planos API** oferece funcionalidades para criação, leitura, atualização (total ou parcial) e exclusão de planos de assinatura. A aplicação foi estruturada utilizando os conceitos do DDD, separando as responsabilidades entre as camadas de domínio, aplicação e infraestrutura.

## Funcionalidades

- **Criação de Planos:** Permite a criação de novos planos de assinatura com informações detalhadas como nome, preço, descrição e duração.
- **Consulta de Planos:** Disponibiliza endpoints para listar todos os planos ou buscar um plano específico por ID.
- **Atualização de Planos:** Suporta atualizações completas (PUT) e parciais (PATCH) para modificar os dados dos planos.
- **Exclusão de Planos:** Permite a remoção de planos de assinatura.
- **Autenticação e Segurança:** Implementa autenticação via JWT e proteção de endpoints utilizando Spring Security.
- **Comunicação Assíncrona:** Utiliza RabbitMQ para gerenciar mensagens entre serviços, facilitando integrações e notificações.
- **Documentação Interativa:** A API é documentada com Swagger, permitindo testes e visualização interativa dos endpoints.

## Tecnologias Utilizadas

- **Spring Boot:** Framework principal para desenvolvimento da aplicação.
- **Spring Security:** Responsável por proteger os endpoints, gerenciando autenticação e autorização.
- **JWT (JSON Web Token):** Utilizado para autenticação segura via tokens.
- **RabbitMQ:** Sistema de mensageria para comunicação assíncrona entre serviços.
- **Swagger / Springdoc OpenAPI:** Ferramenta para documentar e testar a API de forma interativa.

## Arquitetura do Projeto

O projeto foi estruturado segundo os princípios do **Domain Driven Design (DDD)**, onde cada camada tem responsabilidades bem definidas:

- **Domínio:** Contém as regras de negócio e as entidades centrais da aplicação.
- **Aplicação:** Implementa os casos de uso que orquestram as operações da API.
- **Infraestrutura:** Gerencia a persistência de dados, integrações externas (como RabbitMQ) e configurações gerais.

## Estrutura do Projeto

### Models / Entities

- **Descrição:** Representam as classes do domínio que mapeiam a estrutura dos dados e contêm a lógica de negócio essencial. Essas classes geralmente são mapeadas para tabelas do banco de dados.
- **Exemplo:** Classe `Subscription` que modela os planos de assinatura.

### DTO

- **Descrição:** Os **Data Transfer Objects (DTOs)** são utilizados para transportar dados entre as camadas da aplicação, evitando expor as entidades diretamente. Eles ajudam a definir contratos claros de entrada e saída dos dados.
- **Exemplo:** `SubscriptionInputDTO` para criação de planos ou `SubscriptionUpdateDTO` para atualizações parciais.

### Mappers

- **Descrição:** Responsáveis pela conversão entre DTOs e entidades do domínio. Ferramentas como o **MapStruct** podem ser utilizadas para automatizar essa conversão, evitando código repetitivo e manual.
- **Exemplo:** Métodos que transformam `SubscriptionInputDTO` em `Subscription` e atualizam parcialmente uma entidade com dados do `SubscriptionUpdateDTO`.

### Use Cases

- **Descrição:** Camada onde reside a lógica de negócio da aplicação. Cada caso de uso representa uma operação (como criação, atualização, deleção) e orquestra a interação entre as outras camadas.
- **Exemplo:** Classe `SubscriptionUseCase` com métodos para criar, atualizar, buscar e deletar assinaturas.

### Controllers

- **Descrição:** Exposição dos endpoints da API. Os controllers recebem as requisições HTTP, chamam os casos de uso correspondentes e retornam as respostas adequadas, geralmente utilizando DTOs.
- **Exemplo:** `SubscriptionController`, que define endpoints para criar, atualizar (PUT/PATCH), listar e deletar planos.

### Repositories

- **Descrição:** Abstraem a camada de persistência de dados. Os repositórios fornecem métodos CRUD (criação, leitura, atualização e exclusão) para as entidades, facilitando a comunicação com o banco de dados.
- **Exemplo:** `SubscriptionRepository`, que interage com a base de dados para gerenciar as assinaturas.

## Configurações

### Spring Security e JWT

- **Spring Security:** Configurado para proteger os endpoints da API, garantindo que apenas usuários autenticados tenham acesso às funcionalidades restritas.
- **JWT:** Utilizado para autenticação, permitindo a geração e validação de tokens de acesso, assegurando a comunicação segura entre cliente e servidor.

### RabbitMQ

- **Descrição:** Implementado para possibilitar a comunicação assíncrona entre diferentes serviços ou componentes do sistema. Isso garante escalabilidade e desacoplamento das partes da aplicação.
- **Configuração:** As filas e exchanges do RabbitMQ podem ser definidas e configuradas conforme a necessidade, possibilitando a integração com outros sistemas ou microserviços.

### Swagger

- **Descrição:** Ferramenta que gera a documentação interativa da API, facilitando o entendimento e os testes dos endpoints.
- **Configuração:** Utilizando o Springdoc OpenAPI, o Swagger é configurado para mostrar informações detalhadas da API (título, versão, contato, etc.) e para incluir requisitos de segurança com JWT.
- **Acesso:** Após iniciar a aplicação, a documentação pode ser acessada em `http://localhost:8080/swagger-ui/index.html`.


