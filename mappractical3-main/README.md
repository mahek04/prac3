# Database Practical 3 - Spring Boot with MySQL

This is a Spring Boot application integrated with MySQL database using Docker for containerization and persistent volumes.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker and Docker Compose

## Quick Start

### 1. Start MySQL Database

First, start the MySQL database using Docker Compose:

```bash
docker-compose up -d
```

This will:

- Start a MySQL 8.0 container
- Create a database named `practicaldb`
- Create a user `practicaluser` with password `practicalpass`
- Create a persistent volume for data storage
- Initialize the database with sample data

### 2. Run the Spring Boot Application

```bash
./mvnw spring-boot:run
```

Or if you're on Windows:

```cmd
mvnw.cmd spring-boot:run
```

### 3. Test the Application

The application will start on http://localhost:8080

#### Available Endpoints:

- **GET /** - Home page with user count
- **GET /users** - Get all users
- **GET /users/{id}** - Get user by ID
- **POST /users** - Create new user
- **PUT /users/{id}** - Update user
- **DELETE /users/{id}** - Delete user
- **GET /users/search?name={name}** - Search users by name

#### Sample API Calls:

1. **Get all users:**

   ```bash
   curl http://localhost:8080/users
   ```

2. **Create a new user:**

   ```bash
   curl -X POST http://localhost:8080/users \
     -H "Content-Type: application/json" \
     -d '{"name":"Alice Johnson","email":"alice@example.com"}'
   ```

3. **Get user by ID:**

   ```bash
   curl http://localhost:8080/users/1
   ```

4. **Search users:**

   ```bash
   curl "http://localhost:8080/users/search?name=john"
   ```

5. **Update user:**

   ```bash
   curl -X PUT http://localhost:8080/users/1 \
     -H "Content-Type: application/json" \
     -d '{"name":"John Updated","email":"john.updated@example.com"}'
   ```

6. **Delete user:**
   ```bash
   curl -X DELETE http://localhost:8080/users/1
   ```

## Database Configuration

The application connects to MySQL with the following configuration:

- **Host:** localhost:3306
- **Database:** practicaldb
- **Username:** practicaluser
- **Password:** practicalpass

## Data Persistence

The MySQL data is stored in a Docker volume named `mysql_data`, which ensures that your data persists even when the container is stopped or removed.

## Stopping the Application

1. Stop the Spring Boot application with `Ctrl+C`
2. Stop the MySQL container:
   ```bash
   docker-compose down
   ```

To remove the persistent volume as well:

```bash
docker-compose down -v
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/database_practical_3/
│   │   ├── entity/User.java           # JPA Entity
│   │   ├── repository/UserRepository.java  # JPA Repository
│   │   ├── service/UserService.java        # Service Layer
│   │   └── SpringbotMapPractical3Application.java  # Main Controller
│   └── resources/
│       └── application.properties     # Database Configuration
├── docker-compose.yml               # Docker Compose configuration
└── init.sql                        # Database initialization script
```

## Technologies Used

- Spring Boot 3.5.4
- Spring Data JPA
- MySQL 8.0
- Docker & Docker Compose
- Maven
