# Project-PROG2-introduct-spring-boot

This repository contains the final project for the Java programming course (PROG2) of the 2022-2023 academic year. The project focuses on creating RESTful APIs using Spring Boot and the Spring framework, implementing CRUD operations and adhering to the Controller-Service-Repository design pattern.


## Presentation

This project implements a [entities] management system using RESTful APIs. CRUD (Create, Read, Update, Delete) operations are exposed via REST endpoints.

## Technologies used

- Java 8
- Spring Boot
- Spring Web
- JDBC
- PostgreSQL
- Maven 

## Installation

The project follows a structure based on the MVC design pattern:

- `src/main/java/com/monpackage/`
  - `controller/` : REST controllers for endpoints.
  - service/` : Business services.
  - `repository/` : JDBC persistence layers.
  - `model/` : Java classes representing entities.
  - `Application.java` : Main class for starting the application.

## Endpoints
CRUD endpoints are defined in REST controllers. Here are a few examples:

- `GET /api/entities`: Retrieves the list of entities.- `GET /api/entities/{id}`: Retrieves the details of a specific entity.
- POST /api/entities`: Creates a new entity.
- PUT /api/entities/{id}`: Updates the details of an existing entity.
- `DELETE /api/entities/{id}`: Deletes an entity.

1. Clone this Git repository on your local machine.
2. Configure the PostgreSQL database and update the connection information in `src/main/resources/application.properties`.
3. Run the SQL script provided in `sql-scripts` to create the database and insert test data.
4. Open the project in your favorite IDE Intellij
5. Run the application using the main class `Application.java`.

## Project structure
