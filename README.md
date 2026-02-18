# 🚀 Direction Systems

## 📖 Sobre o Projeto

Este projeto consiste em um sistema web completo com autenticação e gerenciamento de produtos, desenvolvido com foco em segurança, organização de código e boas práticas de desenvolvimento.

A aplicação permite que usuários autenticados realizem operações de cadastro e gerenciamento de dados de forma segura, utilizando autenticação baseada em token (JWT).

---

## 🏗️ Arquitetura do Projeto

O sistema está dividido em duas partes:

### 🔹 Backend
- API REST
- Spring Boot
- Spring Security
- Autenticação com JWT
- Proteção de rotas
- Integração com banco de dados
- Operações CRUD completas

### 🔹 Frontend
- HTML
- CSS
- JavaScript
- Consumo de API via fetch
- Armazenamento do token no localStorage
- Controle de sessão
- Redirecionamento automático para login

---

## 🔐 Sistema de Autenticação

O sistema utiliza autenticação baseada em JWT:

1. O usuário realiza login
2. O backend gera um token JWT
3. O token é armazenado no frontend
4. As requisições protegidas enviam o token no header:


5. O backend valida o token antes de permitir acesso às rotas protegidas

---

## 📦 Funcionalidades

✔ Cadastro de usuário  
✔ Login com autenticação  
✔ Geração e validação de token  
✔ Cadastro de produtos  
✔ Listagem de produtos  
✔ Edição de produtos  
✔ Exclusão de produtos  
✔ Proteção de rotas  

---

## 🧠 Conceitos Aplicados

- Arquitetura em camadas (Controller, Service, Repository)
- API REST
- Segurança com Spring Security
- JWT
- Manipulação de DOM
- Tratamento de exceções
- Integração Frontend ↔ Backend

---

## ▶ Como Executar o Projeto

### Backend

1. Clone o repositório:

2. Configure o banco de dados no `application.properties`

3. Execute a aplicação Spring Boot

---

### Frontend

1. Abra o arquivo `index.html`
ou
2. Utilize uma extensão como Live Server no VSCode

---

## 🎯 Objetivo

Este projeto foi desenvolvido com o objetivo de demonstrar conhecimento em:

- Desenvolvimento Backend com segurança
- Integração com Frontend
- Controle de acesso via JWT
- Estruturação profissional de projeto
