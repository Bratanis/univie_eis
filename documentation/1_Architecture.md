---
title: Architecture
type: description
---
# Architecture Components

## 7.1 Backend

### Spring Boot

The backend will be implemented in Java, and the Java Spring Boot framework will be used as it simplifies the setup of the application with minimal configurations and has some key features such as:

- **Spring Security:** For authentication and authorization mechanisms.
- **RESTful Services:** Exposing endpoints for frontend interaction.
- **Spring Data JPA:** For data access and ORM (Object-Relational Mapping) with the relational database, which we are planning to use.

## 7.2 Relational Database

We will use a relational database (e.g., MySQL, PostgreSQL, etc.) to store and manage our data. A rough schema has already been outlined in this document to illustrate the database structure.

## 7.3 Frontend

The frontend will be built with the following structuring and design components:

- HTML
- CSS
- Bootstrap

As for rendering dynamic content and server-side processing, we will use:

### Java Server Pages

Java Server Pages (JSP) enables the development of server-side web applications with the ability to generate dynamic content based on user requests. It simplifies the creation of dynamic web pages through embedding Java code within HTML using tags. JSP was chosen as it is easy to implement and adapt quickly by every member of the team, ensuring that the workflow won’t be slowed down by learning a complex technology.



