# Online Banking System

## 📌 Overview
The *Online Banking System* is a Spring Boot application that provides secure online banking services.  
It allows users to *register, log in, view account details, and perform banking operations* with JWT-based authentication.

---

## 🚀 Features
- User registration & login with JWT authentication
- Secure APIs with Spring Security
- Account management (view account details, balance, transactions, etc.)
- RESTful endpoints for banking operations
- Role-based access control
- Built with Spring Boot & Maven

---

## 🛠 Tech Stack
- *Backend:* Java, Spring Boot, Spring Security, Spring Data JPA
- *Database:* (Configure in application.properties)
- *Authentication:* JWT (JSON Web Token)
- *Build Tool:* Maven

---

## 📂 Project Structure
online-banking/
│── src/main/java/com/banking/online_banking/
│ ├── controller/ # REST Controllers
│ ├── dto/ # Data Transfer Objects
│ ├── entity/ # Database Entities
│ ├── repository/ # JPA Repositories
│ ├── security/ # JWT & Security Config
│ ├── service/ # Service Layer
│ └── OnlineBankingApplication.java
│── src/main/resources/
│ ├── application.properties
│── pom.xml