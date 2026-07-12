# 🔐 Centralized Authentication & SSO with Spring Boot 3

A Spring Boot 3 application demonstrating the implementation of **Centralized Authentication**, **Single Sign-On (SSO)**, **OAuth 2.1**, **OpenID Connect (OIDC)**, and **JWT-based Security** through a series of practical exercises.

---

## ✨ Features

* 🔒 Spring Security
* 🌐 OAuth 2.1 Client
* 👤 OAuth2 Login
* 🆔 OpenID Connect (OIDC)
* ⚙️ Security Configuration
* 📄 Authenticated User Endpoint
* 🪪 OIDC User Information
* 🔑 JWT Token Generation
* 🛡️ JWT Validation
* 🚀 OAuth2 Resource Server
* 🔐 Protected REST APIs

---

# 📁 Project Structure

```text
centralized-auth-sso-exercises
│
├── outputs
│   ├── exercise1.png
│   ├── exercise2.png
│   └── exercise3.png
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/cognizant/authsso
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── jwt
│   │   │       └── AuthSsoApplication.java
│   │   │
│   │   └── resources
│   │       └── application.yml
│
├── pom.xml
└── README.md
```

---

# 🚀 Exercise 1 — OAuth 2.1 & OpenID Connect Authentication

### ✅ Implemented

* Spring Security
* OAuth2 Client
* OAuth2 Login
* OIDC Authentication
* Authenticated User API
* User Claims API

### 🔗 Endpoints

| Method | Endpoint     | Description                                       |
| :----: | ------------ | ------------------------------------------------- |
|   GET  | `/user`      | Returns authenticated user principal              |
|   GET  | `/user-info` | Returns user profile, authorities and OIDC claims |

### 📸 Output

```text
outputs/exercise1.png
```

![Exercise 1](outputs/exercise1.png)

---

# 🔐 Exercise 2 — OAuth2 Resource Server

### ✅ Implemented

* Resource Server
* JWT Authentication
* Authorization Rules
* Protected REST API

### 🔗 Endpoints

#### Generate JWT

```http
GET /auth/token?username=rishabh
```

#### Access Protected API

```http
GET /secure

Authorization: Bearer <JWT_TOKEN>
```

| Method | Endpoint      | Authentication |
| :----: | ------------- | -------------- |
|   GET  | `/auth/token` | Public         |
|   GET  | `/secure`     | JWT Required   |

### 📸 Output

```text
outputs/exercise2.png
```

![Exercise 2](outputs/exercise2.png)

---

# 🪪 Exercise 3 — JWT Secure Communication

### ✅ Implemented

* JWT Provider
* JWT Filter
* JWT Validation
* Secure API
* Token Generation

### 🔗 Endpoints

#### Generate Token

```http
GET /auth/token?username=rishabh
```

#### Access Protected Endpoint

```http
GET /jwt/secure

Authorization: Bearer <JWT_TOKEN>
```

| Method | Endpoint      | Authentication |
| :----: | ------------- | -------------- |
|   GET  | `/auth/token` | Public         |
|   GET  | `/jwt/secure` | JWT Required   |

### 📸 Output

```text
outputs/exercise3.png
```

![Exercise 3](outputs/exercise3.png)

---

# 🌍 General Endpoints

| Method | Endpoint  | Access |
| :----: | --------- | ------ |
|   GET  | `/`       | Public |
|   GET  | `/public` | Public |

---

# 📌 Complete API Reference

| Method | Endpoint                       | Authentication |
| :----: | ------------------------------ | -------------- |
|   GET  | `/`                            | Public         |
|   GET  | `/public`                      | Public         |
|   GET  | `/user`                        | OAuth2 Login   |
|   GET  | `/user-info`                   | OAuth2 Login   |
|   GET  | `/auth/token?username=rishabh` | Public         |
|   GET  | `/secure`                      | JWT Required   |
|   GET  | `/jwt/secure`                  | JWT Required   |

---

# 🛠️ Technology Stack

| Category       | Technologies    |
| -------------- | --------------- |
| Language       | Java 21         |
| Framework      | Spring Boot 3   |
| Security       | Spring Security |
| Authentication | OAuth 2.1, OIDC |
| Authorization  | JWT             |
| Build Tool     | Maven           |

---

# 🎯 Learning Objectives

* Understand OAuth 2.1 Authentication
* Configure OpenID Connect Login
* Build a Resource Server
* Generate & Validate JWT Tokens
* Secure REST APIs using Spring Security
* Implement Centralized Authentication and Single Sign-On (SSO)
