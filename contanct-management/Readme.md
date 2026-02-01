# Contact Management System - Backend

### This is the backend service for the Contact Management System (CMS) built with Spring Boot, using PostgreSQL as the database. It provides CRUD operations and search functionality for managing contacts.


frontend: https://github.com/hawarijawed/Contact-Management-System-Frontend
Table of Contents

Features

Tech Stack

Setup & Run

Docker Setup

API Endpoints

Data Model

CORS

Features

Add a new contact

List all contacts

Update a contact

Delete a contact by ID or delete all contacts

Search contacts by first name, last name, email, contact number, company, or tags

Tags support for categorizing contacts (multiple tags per contact)

RESTful API for integration with frontend

Tech Stack

Backend Framework: Spring Boot

Database: PostgreSQL (running in Docker)

ORM: Hibernate / JPA

Validation: Jakarta Validation API

Logging: SLF4J / Lombok

Frontend Integration: Supports CORS for http://localhost:5173

Setup & Run

Clone the repository:

git clone <repository-url>
cd contact-management-backend


Configure application properties:

Update src/main/resources/application.properties with your PostgreSQL credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/contactdb
spring.datasource.username=xxx
spring.datasource.password=xxx
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Build and run the Spring Boot application:

./mvnw clean install
./mvnw spring-boot:run


The backend will start on http://localhost:8080.

Docker Setup

A PostgreSQL container is provided for local development.

docker-compose.yml:

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


Steps:

Start Docker container:

docker-compose up -d


Verify PostgreSQL is running on localhost:5432.

API Endpoints

All endpoints are prefixed with /api/contact.

1. Get all contacts
   GET /get


Response:

200 OK → Returns a list of contacts

404 Not Found → No contacts exist

2. Add a contact
   POST /add


Request Body (JSON):

{
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"contact": "+1-555-1001",
"company": "Acme Corp",
"notes": "Met at tech conference",
"tags": ["client", "priority"]
}


Response:

200 OK → Returns the created contact

3. Update a contact
   PUT /update/{id}


Request Body (JSON):

{
"firstName": "John",
"lastName": "Doe",
"email": "john.new@example.com",
"contact": "+1-555-1001",
"company": "Acme Corp",
"notes": "Updated notes",
"tags": ["client"]
}


Response:

202 Accepted → Contact updated successfully

400 Bad Request → Error occurred

4. Delete a contact by ID
   DELETE /delete/{id}


Response:

202 Accepted → Contact deleted

400 Bad Request → Deletion failed

5. Delete all contacts
   DELETE /delete


Response:

202 Accepted → All contacts deleted

400 Bad Request → Deletion failed

6. Search contacts
   POST /search


Request Body (JSON):

{
"firstName": "John",
"lastName": "",
"email": "",
"contact": "",
"company": "",
"tags": ["client", "priority"]
}


Response:

200 OK → Returns matching contacts

404 Not Found → No contacts found

Note: tags should always be sent as an array (empty array [] if no tags are provided).

Data Model

Contact Entity:

Field	Type	Description
id	Long	Primary key
firstName	String	Contact first name
lastName	String	Contact last name
email	String	Contact email
contact	String	Contact number
company	String	Company name
notes	String	Optional notes about contact
tags	List<String>	List of tags for the contact
CORS

The backend allows requests from the frontend running on http://localhost:5173:

@CrossOrigin(origins = "http://localhost:5173")
