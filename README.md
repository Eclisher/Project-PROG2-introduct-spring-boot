![Mon mcd](https://github.com/Eclisher/Project-PROG2-introduct-spring-boot/blob/main/new%20MCD.png)
# Project-PROG2-introduct-spring-boot


![GIf](https://github.com/Eclisher/Project-PROG2-introduct-spring-boot/blob/main/EhT6.gif)
This repository contains the final project for the Java programming course (PROG2) of the 2022-2023 academic year. The project focuses on creating RESTful APIs using Spring Boot and the Spring framework, implementing CRUD operations and adhering to the Controller-Service-Repository design pattern.

```sh
My theme is cinema management
```


## Presentation

This project implements a [entities] management system using RESTful APIs. CRUD (Create, Read, Update, Delete) operations are exposed via REST endpoints.

## Technologies used

- Java 17
- Spring Boot
- Spring Web
- JDBC
- PostgreSQL
- Maven 

## Project structure
The project follows a structure based on the MVC design pattern:
- `src/main/java/com.example.com/`
  - `controller/` : REST controllers for endpoints.
  - service/` : Business services.
  - `repository/` : JDBC persistence layers.
  - `model/` : Java classes representing entities.
  - `CinemaApplication.java` : Main class for starting the application.
## Step of manipulation
###  A few steps to follow please:
  - create the salle table as in the **Table.sql** script
then insert all datamocks into **salle.sql**
  - once all datamocks have been inserted **(projection, reservation)**
  - delete the table *salle* with
    ```sh
    drop table salle cascade;
    ```
  - then create the room table with
  - ```sh
    CREATE TABLE salle
	(
			    id            SERIAL PRIMARY KEY,
			    nom           VARCHAR(100) NOT NULL,
			    id_projection int references projection (id),
			    capacite      int   NOT NULL
	);
    ```

 - and then insert the mock data into **salle1.sql**    
## Endpoints
CRUD endpoints are defined in REST controllers. Here are a few examples:

- `GET /entities`: Retrieves the list of entities.-
- `GET /entities/{id}`: Retrieves the details of a specific entity.
- `POST /entities`: Creates a new entity.
- ` PUT /entities/{id}`: Updates the details of an existing entity.
- `DELETE /entities/{id}`: Deletes an entity.

1. Clone this Git repository on your local machine.
2. Configure the PostgreSQL database and update the connection information in `src/main/resources/application.properties`.
3. I've already added the table creation, so all you have to do is insert it into your preferred Postgresql database.
4. Open the project in  IDE Intellij
5. Run the application using the main class `CinemaApplication.java`.

