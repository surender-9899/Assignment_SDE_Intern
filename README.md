# 🚀 Assignment - SDE Intern (Backend)

This project is a **Spring Boot REST API** built as part of the **SDE Intern Backend Assessment**.  
It provides **CRUD operations for users**, integrates with **PostgreSQL**, uses **Spring Security with JWT authentication**, and includes **unit tests** for controllers and services.

---

## 📌 Tech Stack
- **Java 17**
- **Spring Boot 3.2.6**
- **Spring Data JPA**
- **Spring Security (JWT-based authentication)**
- **PostgreSQL**
- **Hibernate (JPA Implementation)**
- **Lombok**
- **JUnit 5 & Mockito** (Unit Testing)
- **Maven** (Build tool)

---

## ✨ Features
- User CRUD (Create, Read, Update, Delete)
- Find User by:
  - ID
  - Username
  - Email
- Passwords are securely stored using `BCryptPasswordEncoder`
- JWT-based authentication & authorization
- Input validation with `@Valid`
- Global error handling
- Unit Tests for Controller and Service layer
- Follows **clean code practices**

---

## 📂 Project Structure
```
Assignment_SDE_Intern/
├── src/main/java/com/Code/Inbound/Assignment_SDE_Intern/
│   ├── controller/          # REST Controllers
│   ├── service/             # Business Logic
│   ├── dao/                 # Repository (JPA)
│   ├── model/               # Entities
│   ├── config/              # Security Config
│   ├── UserDto.java         # DTO for User
│   └── AssignmentSdeInternApplication.java
│
├── src/test/java/com/Code/Inbound/Assignment_SDE_Intern/
│   ├── controller/          # Controller Tests
│   └── service/             # Service Tests
│
├── src/main/resources/
│   └── application.properties
│
├── postman-ss/              # Postman Screenshots
│   ├── get-all-users.png
│   ├── create-user.png
│   ├── update-user.png
│   ├── delete-user.png
│
├── pom.xml
└── README.md
```

---

## ⚙️ Setup & Run Locally

### 1️⃣ Clone the repository
```bash
git clone https://github.com/surender-9899/Assignment_SDE_Intern.git
cd Assignment_SDE_Intern
```

### 2️⃣ Configure PostgreSQL
```sql
CREATE DATABASE assignment_sde_intern;
```

Update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/assignment_sde_intern
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 3️⃣ Run the application
```bash
mvn spring-boot:run
```

The API will start on:  
👉 http://localhost:8080

---

## 📌 API Endpoints

### 🔹 User Endpoints
| Method | Endpoint                         | Description          |
| ------ | -------------------------------- | -------------------- |
| POST   | `/api/users`                     | Create new user      |
| GET    | `/api/users`                     | Get all users        |
| GET    | `/api/users/id/{id}`             | Get user by ID       |
| GET    | `/api/users/username/{username}` | Get user by Username |
| GET    | `/api/users/email/{email}`       | Get user by Email    |
| PUT    | `/api/users/{id}`                | Update user          |
| DELETE | `/api/users/{id}`                | Delete user          |

---

## 🧪 Running Tests

### 1️⃣ Run all unit tests
```bash
mvn test
```

### 2️⃣ Example: Controller Test
- Mocks HTTP requests using MockMvc
- Validates JSON response
- Uses @WithMockUser to simulate authenticated requests

### 3️⃣ Example: Service Test
- Mocks UserRepo
- Verifies CRUD methods

✅ Sample JSON Request:
```json
POST /api/users
{
  "username": "surender",
  "email": "surender@gmail.com",
  "password": "123456"
}
```

---

## 🛡️ Authentication

Currently, endpoints are public for testing.  
To enable JWT-based authentication:
1. Generate token on login
2. Add header:
   ```http
   Authorization: Bearer <token>
   ```

---

## 📸 Screenshots

### 1️⃣ Get All Users
[![Get All Users](postman-ss/get-all-users.png)](https://github.com/surender-9899/Assignment_SDE_Intern/blob/main/Postman%20SS/GetAllUsers.png)

### 2️⃣ Create User
[![Create User](postman-ss/create-user.png)](https://github.com/surender-9899/Assignment_SDE_Intern/blob/main/Postman%20SS/CreateUser.png)

### 3️⃣ Update User
[![Update User](postman-ss/update-user.png)](https://github.com/surender-9899/Assignment_SDE_Intern/blob/main/Postman%20SS/UpdateUser.png)

### 4️⃣ Delete User
[![Delete User](postman-ss/delete-user.png)](https://github.com/surender-9899/Assignment_SDE_Intern/blob/main/Postman%20SS/DeleteUser.png)

---

## 👨‍💻 Author
**Surender Kumar Mahawar**  
GitHub: [surender-9899](https://github.com/surender-9899)
