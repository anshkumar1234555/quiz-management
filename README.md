# Quiz Management System

A backend REST API for managing online quizzes, questions, options, users, attempts, answers, and results.

## Features

- User registration and login
- JWT authentication
- ADMIN and STUDENT roles
- Role-based authorization
- Quiz management
- Question management
- Option management
- Quiz attempts
- Answer submission
- Automatic score calculation
- Result generation
- PostgreSQL database
- REST APIs

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Spring Data JPA
- Hibernate
- Maven
- Lombok
- Postman
- Git & GitHub

## Authentication

The application uses JWT authentication.

After login, use the returned token in protected requests:

Authorization: Bearer <JWT_TOKEN>

## Main APIs

### Authentication

POST /api/auth/register

POST /api/auth/login

### Quizzes

POST /api/quizzes

GET /api/quizzes

GET /api/quizzes/{id}

GET /api/quizzes/{id}/details

PUT /api/quizzes/{id}

DELETE /api/quizzes/{id}

### Questions

POST /api/questions

GET /api/questions

GET /api/questions/{id}

PUT /api/questions/{id}

DELETE /api/questions/{id}

### Options

POST /api/options

GET /api/options

GET /api/options/{id}

PUT /api/options/{id}

DELETE /api/options/{id}

### Attempts

POST /api/attempts

GET /api/attempts

GET /api/attempts/{id}

PUT /api/attempts/{id}/submit

### Answers

POST /api/answers

GET /api/answers

GET /api/answers/{id}

### Results

GET /api/results/{attemptId}

## Roles

### ADMIN

Admin can create, update and delete quizzes, questions and options.

### STUDENT

Students can view quizzes, create attempts, submit answers and view results.

## Database

PostgreSQL is used as the database.

Main entities:

- User
- Category
- Quiz
- Question
- Option
- Attempt
- Answer

## Running the Project

Configure PostgreSQL and your local application.properties file.

Then run:

mvn spring-boot:run

The application runs on:

http://localhost:8080

## Security

The project uses:

- JWT authentication
- BCrypt password hashing
- Stateless Spring Security
- Role-based authorization

Sensitive configuration such as database passwords and JWT secrets is excluded from Git.

## Future Improvements

- React frontend
- Admin dashboard
- Student dashboard
- Swagger/OpenAPI documentation
- Pagination
- Search and filtering
- Docker
- Cloud deployment
- Automated tests