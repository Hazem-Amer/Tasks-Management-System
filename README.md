# Task Management System (Spring Boot)

A simple **Task Management REST API** built with **Spring Boot**, featuring **user authentication (JWT)** and **task CRUD operations**.  
This project demonstrates clean code organization, authentication/authorization setup, and API design best practices.

---

## Features

- **User Authentication** using JWT 24h expiration(Register, Login)
- **Role-based Authorization**
- **Task Management** (CRUD)
- **Global Exception Handling**
- **Unit Testing** using Mockito
- **Postman Tests with Token Reuse**

---

## Tech Stack

- **Java 17+**
- **Spring Boot 3+**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **H2 / MySQL Database**
- **Lombok**
- **Postman (API Testing)**

---

## ⚙️ Setup & Run

### 1️⃣ Clone the repository
```bash
git clone https://github.com/<your-username>/task-management-system.git
cd task-management-system
```

### 2️⃣ Configure the database
#### For MySQL:
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_management
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Build the project
```bash
mvn clean install
```

### 4️⃣ Run the app
```bash
mvn spring-boot:run
```

The API will be available at:
```
http://localhost:8080
```


## 🧪 Postman Testing

### Import the Postman Collection
1. Open 🔗 Postman Collection Link
You can test all APIs using this shared Postman collection:  
👉 **[View on Postman](https://documenter.getpostman.com/view/34796608/2sB3Wk14eP)** 


### make sure to Login first via:
```
**POST** `/auth/login`
```
so that the jwt Token will be saved as an enviroment global variable(bearer token section)  Automatically to be able to test all the other endpoints

