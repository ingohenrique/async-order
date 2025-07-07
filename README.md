# Sistema de Pedidos Assíncronos

Sistema backend para criação e processamento assíncrono de pedidos usando fila de mensagens.

## Tecnologias

- Java 21
- Spring Boot 3.5.3
- RabbitMQ
- Maven
- Docker Compose

## Endpoints

### Criar Pedido

```
POST /pedidos
Content-Type: application/json

{
  "clienteId": "cliente-123",
  "itens": [
    {
      "id": "item-1",
      "nome": "Produto A",
      "quantidade": 2,
      "preco": 29.99
    }
  ]
}
```

### Consultar Pedido

```
GET /pedidos/{id}
```

Retorna o status atual do pedido: `PENDENTE` ou `PROCESSADO`.

## Como executar

1. Subir o RabbitMQ:

```bash
docker-compose up -d
```

2. Executar a aplicação:

```bash
./mvnw spring-boot:run
```

A aplicação ficará disponível em `http://localhost:8080`.

O RabbitMQ Management pode ser acessado em `http://localhost:15672` (guest/guest).

## Processamento

Os pedidos são processados automaticamente após 2 segundos via fila de mensagens. O status é atualizado de `PENDENTE` para `PROCESSADO`.
