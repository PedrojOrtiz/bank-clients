# 🏦 Bank Clients Microservice

This microservice handles all **person** and **client** information within the banking platform. It works in coordination with the `bank-transactions` microservice for account validation and transaction orchestration using a **Saga choreography architecture** and a **Template Method pattern** for business logic.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 3
- Gradle
- PostgreSQL
- WebFlux WebClient
- RabbitMQ
- Docker Compose

---

## 🧩 Architecture

- **Template Method Pattern** for consistent service logic structure.
- **Saga Choreography** for managing distributed transactions.
- **RabbitMQ** for asynchronous messaging.
- **WebClient (WebFlux)** for non-blocking HTTP calls between services.

---

## ✅ Testing

- ✅ Unit Tests
- ✅ Integration Tests

---

## 🐳 Deployment

Includes a `deploy.sh` script that:

1. Builds both `bank-clients` and `bank-transactions` microservices.
2. Launches them using Docker Compose.

> ⚠️ **Important:** Both services must reside in the **same directory** before running the script.

### 🔧 Run

```bash
./deploy.sh
