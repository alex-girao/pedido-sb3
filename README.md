# 📦 Projeto Pedido

Este é um projeto de demonstração usando **Spring Boot 3**, com integração ao **MongoDB** e ao **RabbitMQ**.
Ele utiliza o Java 21 e foi estruturado para ser executado de forma simples com suporte a testes e containers via Docker.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Web
- Spring Data MongoDB
- Spring AMQP (RabbitMQ)
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

## 📋 Modelo de Dados

### Entidade OrderEntity

```java
@Document(collection = "pedidos")
public class PedidoEntity {

    @MongoId
    private Long pedidoId;

    @Indexed(name = "cliente_id_index")
    private Long clienteId;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;

    private List<PedidoItem> itens;

}
```

---

## ▶️ Como Executar o Projeto

1. **Clone o repositório**

```bash
git clone https://github.com/alex-girao/pedido-sb3.git
cd pedido-sb3
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

## 📡 API Endpoints

### Consultar Pedidos de um Cliente

Recupera todos os pedidos associados a um cliente específico, com suporte à paginação.

#### Requisição

```bash
curl --request GET \
--url http://localhost:8080/clientes/{clienteId}/pedidos
```

Exemplo:
```bash
curl --request GET \
--url http://localhost:8080/clientes/2/pedidos
```

#### Parâmetros de URL

- `clienteId` (obrigatório): ID do cliente cujos pedidos você deseja consultar
- `page` (opcional): Número da página (padrão: 0)
- `size` (opcional): Tamanho da página (padrão: 10)

#### Resposta

**Status Code:** 200 OK

```json
{
  "summary": {
    "totalPedidos": 50
  },
  "data": [
    {
      "pedidoId": 1003,
      "clienteId": 2,
      "total": 50
    }
  ],
  "pagination": {
    "page": 0,
    "pageSize": 10,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

#### Descrição dos Campos

- `summary`: Resumo dos dados retornados
    - `totalPedidos`: Total de pedidos associados ao cliente
- `data`: Lista de pedidos do cliente
    - `pedidoId`: Identificador único do pedido
    - `clienteId`: Identificador do cliente
    - `total`: Valor total do pedido
- `pagination`: Informações de paginação
    - `page`: Número da página atual
    - `pageSize`: Tamanho da página
    - `totalElements`: Total de elementos encontrados
    - `totalPages`: Total de páginas disponíveis

---

## 🔍 Dicas de Implementação

### MongoDB e Spring Data

- Use `@Document(collection = "nome_colecao")` para definir coleções
- Certifique-se de usar a propriedade `collection` (não `collation`) na anotação `@Document`
- Utilize `@CompoundIndex` para criar índices personalizados
- Para campos decimais, use `@Field(targetType = FieldType.DECIMAL128)` com `BigDecimal`

### Paginação

A aplicação usa o `Pageable` do Spring Data para paginação:

```java
public interface PedidoRepository extends MongoRepository<PedidoEntity, Long> {
    Page<PedidoEntity> findAllByClienteId(Long clienteId, PageRequest pageRequest);
}
```

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

Um exemplo de mensagem
```json
{
  "codigoPedido": 7223,
  "codigoCliente":1,
  "itens": [
    {
      "produto": "tesoura",
      "quantidade": 5,
      "preco": 10.00
    },
    {
      "produto": "apontador",
      "quantidade": 5,
      "preco": 1.00
    }
  ]
}
```
---

## 📁 Estrutura do Projeto

```
pedido-sb3/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/alexgirao/pedido/
│   │   │       ├── controller/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── PedidoApplication.java
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