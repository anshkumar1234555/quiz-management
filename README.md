# Quiz Management System

A backend REST API for managing quizzes, questions, options, student attempts, answers, authentication, and results.

## Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Postman

## Features

### Authentication

* User registration
* User login

**API Endpoints:**

```text
POST /api/auth/register
POST /api/auth/login
```

### Quizzes

```text
POST /api/quizzes
GET /api/quizzes
GET /api/quizzes/{id}
GET /api/quizzes/{id}/details
PUT /api/quizzes/{id}
DELETE /api/quizzes/{id}
```

### Questions

```text
POST /api/questions
GET /api/questions
GET /api/questions/{id}
PUT /api/questions/{id}
DELETE /api/questions/{id}
```

### Options

```text
POST /api/options
GET /api/options
GET /api/options/{id}
PUT /api/options/{id}
DELETE /api/options/{id}
```

### Attempts

```text
POST /api/attempts
GET /api/attempts
GET /api/attempts/{id}
PUT /api/attempts/{id}/submit
```

### Answers

```text
POST /api/answers
GET /api/answers
GET /api/answers/{id}
GET /api/answers/attempt/{attemptId}
```

### Results

```text
GET /api/results/{attemptId}
```

## Roles

### ADMIN

Admin can:

* Create quizzes
* Update quizzes
* Delete quizzes
* Create categories
* Create questions
* Create multiple-choice options
* Mark correct options
* View quiz details

### STUDENT

Students can:

* View quizzes
* Login using JWT
* Start quiz attempts
* Submit answers
* Submit attempts
* View final results

## Database

PostgreSQL is used as the database.

### Main Entities

* User
* Category
* Quiz
* Question
* Option
* Attempt
* Answer

## Security

The project uses:

* JWT authentication
* Role-based authorization
* ADMIN and STUDENT roles
* BCrypt password encryption

## API Flow

```text
Register / Login
       ↓
JWT Authentication
       ↓
ADMIN creates Category
       ↓
ADMIN creates Quiz
       ↓
ADMIN creates Question
       ↓
ADMIN creates Options
       ↓
STUDENT starts Attempt
       ↓
STUDENT submits Answer
       ↓
STUDENT submits Attempt
       ↓
System calculates Score
       ↓
Student views Result
```

## Running the Project

### 1. Clone the Repository

```bash
git clone https://github.com/anshkumar1234555/quiz-management.git
```

```bash
cd quiz-management
```

### 2. PostgreSQL Setup

Create a PostgreSQL database and configure the database details in your local `application.properties`.

Do not upload passwords, database credentials, JWT secrets, or other sensitive information to GitHub.

Use `application-example.properties` as a template for the required configuration.

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Project

```bash
mvn spring-boot:run
```

The application will start as a Spring Boot REST API.

## API Testing

Postman can be used to test the REST API endpoints.

Recommended flow:

1. Register a user.
2. Login and receive the JWT token.
3. Use the JWT token for authenticated requests.
4. ADMIN creates categories, quizzes, questions, and options.
5. STUDENT starts an attempt.
6. STUDENT submits answers.
7. STUDENT submits the attempt.
8. View the final result.

## Future Improvements

* Add pagination and sorting.
* Add improved exception handling.
* Add Swagger/OpenAPI documentation.
* Add automated unit and integration tests.
* Add email notifications.
* Add quiz time limits.
* Add improved result and performance analytics.


## License

This project is created for educational and project purposes.
