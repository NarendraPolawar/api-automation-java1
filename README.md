# API Automation Framework (Java + RestAssured)
## Overview
This project automates testing for FastAPI endpoints using REST Assured and TestNG.

## Technologies
- Java
- REST Assured
- TestNG
- Allure
- Maven
- GitHub Actions

## Run Locally
```bash
mvn clean test -DbaseUrl=http://localhost:8000
mvn allure:serve
```

## Features
- CRUD API tests
- Positive & negative cases
- Allure reporting
- CI/CD with GitHub Actions
