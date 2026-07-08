# Spring Kafka Practice

A hands-on project for learning Apache Kafka by building an event-driven microservice architecture with Spring Boot.

This repository explores the fundamentals of Apache Kafka and demonstrates how asynchronous communication can be used to build loosely coupled microservices through a simple event-driven architecture.

---

## Architecture

<img width="700" alt="3" src="https://github.com/user-attachments/assets/9e5a0f0a-3eb9-4a90-a0f9-ab6704b49dad" />


In this example:

- The **User Service** handles user registration requests.
- User information is stored in the **User Database**.
- After registration, the service publishes a `UserCreated` event to **Kafka**.
- The **Email Service** consumes the event and simulates sending a welcome email.
- Each service owns its own database and communicates only through Kafka.

---

## Why Kafka?

Traditional synchronous communication (e.g., REST APIs) tightly couples services together. If a downstream service becomes unavailable or responds slowly, it can directly affect the user request and reduce the overall reliability of the system.

Apache Kafka enables asynchronous communication by allowing services to exchange events instead of direct requests. This improves service decoupling, fault isolation, and scalability.

In this project, the **User Service** publishes a `UserCreated` event after successfully registering a user. The **Email Service** independently consumes the event and simulates sending a welcome email without affecting the registration process. This demonstrates how event-driven communication allows services to operate independently while keeping the registration flow simple and resilient.


### Kafka in Core Banking

In core banking systems, customer transactions such as deposits, withdrawals, or transfers are completed first as the critical business operation.

After the transaction succeeds, additional processes—including notifications, audit logging, fraud detection, and analytics—can be handled asynchronously through Kafka. This keeps the transaction response fast while allowing downstream services to process events independently.

Although this project uses a user registration example, it demonstrates the same event-driven communication pattern commonly adopted in financial systems.


---

## Tech Stack

- Java 21
- Spring Boot
- Apache Kafka
- Spring for Apache Kafka
- Spring Data JPA
- H2 Database
- AWS EC2
- Gradle

---

## Key Concepts

- Apache Kafka fundamentals
- Producer & Consumer
- Topics
- Consumer Groups
- Offsets
- Event-driven Architecture
- Asynchronous Messaging
- Service Decoupling

---

## Related Projects

| Repository | Description |
|------------|-------------|
| [spring-kafka-user-service](https://github.com/zzzyoonnn/spring-kafka-user-service) | Producer Service |
| [spring-kafka-email-service](https://github.com/zzzyoonnn/spring-kafka-email-service) | Consumer Service |

---

## Event Flow

1. Client sends a signup request.
2. User Service validates the request.
3. User information is persisted in H2.
4. User Service publishes a UserCreated event to Kafka.
5. Kafka stores and delivers the event.
6. Email Service consumes the event.
7. Email Service processes the event.
8. A welcome email is simulated through logging.
