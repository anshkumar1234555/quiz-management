# Quiz Management System

A backend REST API for managing quizzes, questions, options, student attempts, answers, authentication, and results.

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Postman

## Features

### Authentication
- User registration
- User login
- JWT authentication
- Role-based authorization
- ADMIN and STUDENT roles
- BCrypt password encryption

### Admin Features
- Create quizzes
- Update quizzes
- Delete quizzes
- Create categories
- Create questions
- Create multiple-choice options
- Mark correct options
- View quiz details

### Student Features
- Login using JWT
- Start quiz attempts
- Submit answers
- Submit attempts
- Automatic score calculation
- Percentage calculation
- View final results

## API Flow

```text
Register/Login
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
