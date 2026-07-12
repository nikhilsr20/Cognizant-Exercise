# User Service

Part of **Exercise 1 – User and Order Management System**.

Manages user records for the system. This is the service that Order
Service calls (via OpenFeign) to validate that a user exists before an
order can be placed.

## Tech Stack
- Spring Boot 3.2.5
- Spring Web (REST)
- Spring Data JPA
- MySQL
- Bean Validation
- Lombok

## Package Structure
```
com.cognizant.userservice
├── controller     # REST endpoints
├── service        # Business logic (interface + impl)
├── repository     # Spring Data JPA repository
├── entity         # JPA entities
└── exception      # Custom exceptions + global exception handler
```

## Port
`8081`

## Database
MySQL database `user_db` (auto-created). Update credentials in
`src/main/resources/application.yml` if needed:
```yaml
spring.datasource.url: jdbc:mysql://localhost:3306/user_db?createDatabaseIfNotExist=true
spring.datasource.username: root
spring.datasource.password: root
```

## Endpoints

| Method | Path              | Description         |
|--------|-------------------|----------------------|
| POST   | `/api/users`      | Create a user        |
| GET    | `/api/users`      | List all users        |
| GET    | `/api/users/{id}` | Get user by id        |
| PUT    | `/api/users/{id}` | Update a user         |
| DELETE | `/api/users/{id}` | Delete a user         |

### Sample request
```http
POST /api/users
Content-Type: application/json

{
  "name": "Asha Rao",
  "email": "asha.rao@example.com",
  "phone": "9876543210",
  "address": "Bengaluru, India"
}
```

### Sample response
```json
{
  "id": 1,
  "name": "Asha Rao",
  "email": "asha.rao@example.com",
  "phone": "9876543210",
  "address": "Bengaluru, India"
}
```

## Running locally
```bash
cd user-service
mvn spring-boot:run
```

The service starts on `http://localhost:8081`.
