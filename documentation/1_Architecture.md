---
type: description
---
# Architecture Components

![UML component diagram](./models/component.svg)

## 7.1 Backend

### Spring Boot

The backend is implemented in Java, and the Java Spring Boot framework is used as it simplifies the setup of the application with minimal configurations and has some key features such as:

- **Spring Security:** For authentication and authorization mechanisms.
- **RESTful Services:** Exposing endpoints for frontend interaction.
- **Spring Data JPA:** For data access and ORM (Object-Relational Mapping) with the relational database, which we are planning to use.

## 7.2 Relational Database

The database is inside a docker volume for persistance. This volume is mounted to a MySQL container that is used to manage the data. The ER-diagram below shows the structure of the database:
![ER-model of DB](./models/erm.svg)


## 7.3 Frontend

The frontend is built with the following structuring and design components:

- HTML
- CSS
- Bootstrap

the Graphs displayed on the website are renderes using CanvasJS.



