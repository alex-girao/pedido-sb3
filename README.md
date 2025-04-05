# 📦 Projeto Pedido

Este é um projeto de demonstração usando **Spring Boot 3.4.4**, com integração ao **MongoDB** e ao **RabbitMQ**. 
Ele utiliza o Java 21 e foi estruturado para ser executado de forma simples com suporte a testes e containers via Docker.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Web
- Spring Data MongoDB
- Spring AMQP (RabbitMQ)
- Testes com Spring Boot Starter Test e RabbitMQ Test
- MongoDB (em container)
- RabbitMQ (em container)

---

## ⚙️ Configuração do Ambiente

A aplicação depende do MongoDB e do RabbitMQ. Você pode subir ambos os serviços com Docker usando o arquivo `docker-compose.yml` incluso no projeto.

### 🐳 Arquivo `docker-compose.yml`

```yaml
services:
  mongodb:
    image: mongo
    ports:
      - 27017:27017
    environment:
      - MONGO_INITDB_ROOT_USERNAME=admin
      - MONGO_INITDB_ROOT_PASSWORD=123

  rabbitmq:
    image: rabbitmq:3.13-management
    ports:
      - 15672:15672
      - 5672:5672
```

### 🔌 Subindo os serviços

```bash
docker-compose up -d
```

---

## 🛠️ Configurações da Aplicação

A aplicação já vem configurada para se conectar ao MongoDB e RabbitMQ locais com as seguintes credenciais:

### 🔧 Arquivo `application.properties`

```properties
spring.application.name=pedido

spring.data.mongodb.authentication-database=admin
spring.data.mongodb.auto-index-creation=true
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=pedido_db
spring.data.mongodb.username=admin
spring.data.mongodb.password=123
```

---

## ▶️ Como Executar o Projeto

1. **Clone o repositório**

```bash
git clone https://github.com/seu-usuario/pedido.git
cd pedido
```

2. **Suba os serviços com Docker**

```bash
docker-compose up -d
```

3. **Compile e execute o projeto**

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

---

## ✅ Executando os Testes

```bash
mvn test
```

---

## 🧪 Acesso ao RabbitMQ

Acesse o painel de administração do RabbitMQ:

- URL: http://localhost:15672
- Usuário padrão: `guest`
- Senha padrão: `guest`

---

## 📁 Estrutura do Projeto

```
pedido/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## 👤 Autor

**Alex Girao**  
🔗 [[Linkedin](https://br.linkedin.com/in/alex-girao)]

---

## 📄 Licença

Este projeto está sob a licença MIT.

---
