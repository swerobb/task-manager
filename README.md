# Task Manager REST API

A simple but production-style **Task Manager REST API** built with Java and Spring Boot.  
This purpose of this project is to focuses on clean architecture, validation, testing, and predictable API behavior.

It is designed as a **portfolio backend project** with no UI, tested via HTTP clients (`curl`).

---

## Tech Stack

- Java 17
- Spring Boot 3.2.x
- Spring Web (REST)
- Spring Data JPA
- PostgreSQL (runtime)
- H2 (tests)
- Maven
- JUnit 5
- Git & GitHub

---

## Features

- Create, read, update, and delete tasks
- Enum-based task status (`TODO`, `IN_PROGRESS`, `DONE`)
- Input validation with clear error responses
- Global exception handling
- Pagination and sorting
- Isolated repository tests
- Clean DTO-based API (entities not exposed)

---

## Task Model

```json
{
  "id": 1,
  "title": "Finish backend project",
  "status": "TODO",
  "createdAt": "2026-01-17T15:35:57Z"
}
