# 🍽️ Sistema de Reservas – Tra Amici Bistrô

Este projeto é um sistema web para gerenciamento de reservas em restaurantes, com foco na otimização do atendimento, organização de mesas e melhoria da experiência do cliente.

Clientes podem reservar mesas online, consultar horários disponíveis, editar ou cancelar reservas. Já os administradores têm acesso a um painel para gerenciar a disponibilidade de mesas, acompanhar agendamentos em tempo real e promover eventos e campanhas promocionais.

> **🧑‍🏫 Este projeto foi feito como atividade para a cadeira de Arquitetura de Software e Desenvolvimento em Nuvem.**

---

## 🔧 Funcionalidades

- Cadastro e autenticação de clientes e administradores
- Agendamento de reservas com data, horário e número de pessoas
- Edição e cancelamento de reservas
- Gerenciamento da disponibilidade de mesas pelo administrador
- Envio de notificações automáticas (e-mail e SMS)
- Painel administrativo com visão geral das reservas
- Envio de promoções e eventos para clientes cadastrados

---

## 🛠️ Tecnologias Utilizadas

### Frontend
- [React.js](https://reactjs.org/) com [TypeScript](https://www.typescriptlang.org/)

### Backend
- [Java](https://www.java.com/) com [Spring Boot](https://spring.io/projects/spring-boot)

### Banco de Dados
- [PostgreSQL](https://www.postgresql.org/)
- [JPA](https://jakarta.ee/specifications/persistence/)
- [Hibernate](https://hibernate.org/)

### Autenticação
- [Firebase Auth](https://firebase.google.com/products/auth)

### Infraestrutura e Monitoramento
- [Docker](https://www.docker.com/)
- [AWS](https://aws.amazon.com/)
- [Prometheus](https://prometheus.io/)
- [Grafana](https://grafana.com/)

---

## 🚀 Como Executar o Projeto

### 🔹 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/sistema-reservas-bistro.git
cd sistema-reservas-bistro
```

### 🔹 2. Inicie o banco de dados (PostgreSQL via Docker)

```bash
cd reservas-backend/database-docker
docker-compose up -d
```

### 🔹 3. Execute o backend

- Abra o projeto `reservas-backend` em sua IDE (IntelliJ, VSCode, etc).
- Rode a classe principal do Spring Boot (`@SpringBootApplication`).

### 🔹 4. Execute o frontend

```bash
cd reservas-frontend
npm install
npm start
```

---

## 📌 Observações

- Certifique-se de que a porta `5432` (PostgreSQL) esteja livre antes de rodar o Docker.
- Os dados serão armazenados em volume persistente definido no `docker-compose.yml`.