# Student Management REST API

A RESTful backend application built using **Java, Spring Boot, Spring Data JPA, and PostgreSQL** to manage student records.

This project demonstrates backend development concepts including REST API design, CRUD operations, layered architecture, database integration, input validation, exception handling, and automated testing.

## Features

- **CRUD Operations:** Create, retrieve, update, and delete student records through REST endpoints.
- **PostgreSQL Integration:** Persist student information using Spring Data JPA.
- **Layered Architecture:** Separate HTTP request handling, business logic, and database access.
- **Input Validation:** Validate required student fields using Jakarta Bean Validation.
- **Exception Handling:** Handle application errors, including requests for nonexistent students.
- **HTTP Status Codes:** Return appropriate responses for API operations.
- **Automated Testing:** Implement controller, service, and integration tests using JUnit, Mockito, and MockMvc.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Backend programming language |
| Spring Boot | Application framework |
| Spring Web MVC | REST API development |
| Spring Data JPA | Database persistence |
| PostgreSQL | Relational database |
| Hibernate | JPA implementation |
| Jakarta Bean Validation | Input validation |
| Maven | Build and dependency management |
| JUnit 5 | Automated testing |
| Mockito | Mocking dependencies |
| MockMvc | Testing HTTP endpoints |
| Git & GitHub | Version control |

## Application Architecture

The application follows a layered structure:

```text
Client (Postman / HTTP Client)
            |
            v
   StudentController
     REST Endpoints
            |
            v
     StudentService
     Business Logic
            |
            v
    StudentRepository
      Spring Data JPA
            |
            v
      PostgreSQL
```

**StudentController:** Receives HTTP requests and returns API responses.

**StudentService:** Implements business logic for student operations.

**StudentRepository:** Handles database access using Spring Data JPA.

**Student:** JPA entity representing a student record.

**GlobalExceptionHandler:** Centralizes handling of application exceptions.

## REST API Endpoints

Base URL: `http://localhost:9090`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/students` | Retrieve all students |
| GET | `/students/{id}` | Retrieve a student by ID |
| POST | `/students` | Create a new student |
| PUT | `/students/{id}` | Update an existing student |
| DELETE | `/students/{id}` | Delete a student |

An additional `GET /student` endpoint returns a sample student object for demonstration purposes.

### Example — Create a Student

**Request**

```http
POST /students
Content-Type: application/json
```

```json
{
  "studentName": "Sreya",
  "studentCourse": "Computer Science"
}
```

**Example successful response — HTTP 201 Created**

```json
{
  "studentId": 1,
  "studentName": "Sreya",
  "studentCourse": "Computer Science"
}
```

The database generates the student ID.

## Database Integration

The application uses PostgreSQL for persistent storage.

The `Student` entity maps to the `spring_students` table and contains:

| Field | Description |
|---|---|
| studentId | Primary key, auto-generated |
| studentName | Student name, required |
| studentCourse | Student course, required |

JPA annotations such as `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, and `@Column` define the entity mapping.

Database scripts are available under `src/docs/ddl/` and `src/docs/dml/`.

## Getting Started

### Prerequisites

- JDK 21
- PostgreSQL
- Git
- A Java IDE or terminal

### 1. Clone the Repository

```bash
git clone https://github.com/ragasreya-choppa/student-management-rest-api.git
cd student-management-rest-api
```

### 2. Configure PostgreSQL

Create a PostgreSQL database named:

```text
student_management
```

Use the schema provided in:

```text
src/docs/ddl/student-management-ddl.sql
```

The application uses an existing database schema rather than automatically generating tables.

### 3. Configure Database Credentials

The main `application.properties` uses:

```properties
server.port=9090

spring.datasource.url=jdbc:postgresql://localhost:5432/student_management
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=none
```

Set the `DB_PASSWORD` environment variable to your local PostgreSQL password.

On macOS or Linux:

```bash
export DB_PASSWORD="your_local_database_password"
```

Use your actual local database credentials. Do not commit passwords to Git.

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

Once the application starts, access the endpoints through Postman or another HTTP client.

Example:

```http
GET http://localhost:9090/students
```

## Testing

Automated tests are included to verify the application's functionality.

### Controller Testing

Uses MockMvc to test HTTP requests and responses.

### Service Testing

Uses JUnit and Mockito to verify business logic and mock dependencies.

### Integration Testing

Uses Spring Boot's testing support, MockMvc, and a PostgreSQL test database to validate the interaction between application layers.

The integration tests cover scenarios including:

- Retrieving student records
- Creating new students
- Updating existing students
- Deleting students
- Rejecting blank student names and courses
- Handling requests involving nonexistent student IDs

### Run Tests

Create a separate PostgreSQL database:

```text
student_management_test
```

Apply the required database schema and ensure `DB_PASSWORD` is configured.

Then run:

```bash
./mvnw clean test
```

The integration tests use the `test` Spring profile and the configuration in `src/test/resources/application-test.properties`.

## Key Concepts Practiced

Through this project, I gained hands-on experience with:

- Building RESTful APIs using Spring Boot
- Applying HTTP methods and status codes
- Implementing layered backend architecture
- Using dependency injection
- Mapping Java objects to relational tables using JPA
- Performing database operations with Spring Data JPA
- Implementing input validation and exception handling
- Writing unit tests using JUnit and Mockito
- Testing REST endpoints using MockMvc
- Performing integration tests with PostgreSQL
- Using Git commits to track development progress

## Future Improvements

- Add pagination and sorting for student records
- Introduce DTOs to separate API requests from persistence entities
- Generate API documentation using OpenAPI/Swagger
- Add API authentication and authorization
- Configure GitHub Actions for automated builds and testing
- Improve API error response consistency

## Development History

This project was developed incrementally while learning Java backend development.

The Git commit history documents the implementation of REST endpoints, database integration, validation, exception handling, unit tests, and integration tests.

The repository preserves this development history to demonstrate the progression of the project.

## Author

**Sreya**

Computer Science Graduate | Aspiring Java Backend Developer

GitHub: [ragasreya-choppa](https://github.com/ragasreya-choppa)

Project Repository: [student-management-rest-api](https://github.com/ragasreya-choppa/student-management-rest-api)