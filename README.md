# CodeAlpha Backend Project: Event Registration System

REST API built with Java 17, Spring Boot, Spring Data JPA, validation, and MySQL.

## Features
- Create and list events
- View event details and current seat usage
- Register a person for an event
- Prevent duplicate active registrations for the same event/email
- Enforce event capacity
- View registrations by email
- Cancel a registration (cancelled registrations no longer occupy a seat)
- Consistent 400, 404, and 409 error responses

## Requirements
- JDK 17+
- Maven 3.6+
- MySQL 8+
- Postman

## Setup
1. Create database (or let the JDBC URL create it):
   ```sql
   CREATE DATABASE codealpha_events;
   ```
2. Open `src/main/resources/application.properties` and replace `your_mysql_password`.
   Or set environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`.
3. Run:
   ```bash
   mvn spring-boot:run
   ```
4. Base URL: `http://localhost:8080`.

## API examples

### Create event
`POST /api/events`
```json
{
  "title": "Spring Boot Workshop",
  "description": "Beginner-friendly REST API workshop",
  "venue": "College Seminar Hall",
  "startsAt": "2027-01-20T10:00:00",
  "capacity": 50
}
```
Use a future `startsAt` value.

### List events
`GET /api/events`

### Get event details
`GET /api/events/1`

### Register for an event
`POST /api/events/1/registrations`
```json
{
  "name": "Asha Kumar",
  "email": "asha@example.com"
}
```

### View registrations by email
`GET /api/registrations?email=asha@example.com`

### Cancel registration
`PATCH /api/registrations/1/cancel`

## Notes
- This starter project does not include authentication or an admin UI; add them if required by your mentor.
- Set your own database password locally. Never commit passwords or secrets to GitHub.
- Run and test every endpoint before submission, and be prepared to explain the code.
