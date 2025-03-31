# Java Monorepo Example

This is a simple Java monorepo with two isolated service submodules.

## Project Structure

```
java-monorepo/
├── parent-pom/           # Common parent POM with shared configurations
├── service-a/            # User Service API (running on port 8080)
├── service-b/            # Product Service API (running on port 8081)
├── pom.xml               # Root aggregator POM
└── README.md             # This README file
```

## How to Build

To build all services:

```bash
./mvnw clean install
```

To build a specific service:

```bash
./mvnw clean install -pl service-a
```

## How to Run

### Service A (User Service)

```bash
cd service-a
../mvnw spring-boot:run
```

Access at http://localhost:8080/api/users/1

### Service B (Product Service)

```bash
cd service-b
../mvnw spring-boot:run
```

Access at http://localhost:8081/api/products/1

## Features

- **Isolated Services**: Each service is completely independent
- **Centralized Configuration**: Common settings in parent POM
- **Spring Boot**: Each service is a standalone Spring Boot application
