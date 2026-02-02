# Contact Management System - Backend

 This is the backend service for the Contact Management System (CMS) built with Spring Boot, using PostgreSQL as the database. It provides CRUD operations and search functionality for managing contacts.


**frontend repository:** https://github.com/hawarijawed/Contact-Management-System-Frontend


## 📋 Table of Contents
* Features
* Tech Stack
* Setup & Run
* Docker Setup
* API Endpoints
* Data Model
* CORS

## ✨ Features
* **Create & Update:** Seamlessly add new contacts or modify existing ones
* **Bulk Management:** List all contacts or clear the entire database with a single request.

* **Delete Contact:** Delete a contact by ID
* **Search Contact:** Search contacts by first name, last name, email, contact number, company, or tags
* **Taging System**: Tags support for categorizing contacts (multiple tags per contact)

## 🛠️ Tech Stack
* **Backend Framework:** Spring Boot
* **Database:** PostgreSQL (running in Docker)
* **ORM:** Hibernate / JPA
* **Validation:** Jakarta Validation API
* **Logging:** SLF4J / Lombok
* **Frontend Integration:** Supports CORS for http://localhost:5173

## 🚀 Setup & Run

1. **Clone the repository**
   ```declarative
   git clone <repository-url>
   cd contact-management-backend
   ```

2. **Configure application properties:**

Update `src/main/resources/application.yml` with your PostgreSQL credentials:
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/contactdb
    username: your_username
    password: your_password
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update   # use validate in prod
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect

server:
  port: 8080
```

3. **Build and run the Spring Boot application:**
```
./mvnw clean install
./mvnw spring-boot:run
```
The backend will start on `http://localhost:8080`.`

## 🐳 Docker Setup

A PostgreSQL container is provided for local development.

`docker-compose.yml:`
```
version: "3.9"

services:
postgres:
image: postgres:16
container_name: cms-postgres
restart: always
environment:
POSTGRES_DB: contactdb
POSTGRES_USER: xxx
POSTGRES_PASSWORD: xxx
ports:
- "5432:5432"
volumes:
- postgres_data:/var/lib/postgresql/data

volumes:
postgres_data:
```

### Steps:

1. Start Docker container:
`docker-compose up -d`
2. Verify PostgreSQL is running on `localhost:5432`.

## 📡 API Endpoints

All endpoints are prefixed with `/api/contact`.

1. **Get all contacts:** `GET: /get`
2. **Add new contact:** `POST: /add`
3. **Update an existing contact:** `PUT: /update/{id}`
4. **Delete Contact:** `DELETE: /delete/{id}`
5. **Search Contact:** `POST: /search`



## 📊 Data Model

**Contact Entity**

| Field	     | Type	| Description| 
|------------| ---| ---|
| `id`	      |`Long`	|Primary key|
| `firstName` |	`String`	|Contact first name|
| `lastName` | `String`	|Contact last name|
| `email`	   | `String`	| Contact email|
| `contact`	| `String`   |	Contact number|
| `company`	| `String`	| Company name|
|`notes`   | `String`	|Optional notes about contact|
| `tags`	|`List<String>`	|List of tags for the contact|


## 🔒CORS

The backend allows requests from the frontend running on `http://localhost:5173:`

`@CrossOrigin(origins = "http://localhost:5173")`
