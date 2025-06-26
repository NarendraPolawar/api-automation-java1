## API Automation Framework (Java + RestAssured)
# Overview
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

# Modular Structure

We created a BaseTest class to centralize common setup logic like base URL.
Each API operation (Create, Read, Update, Delete) has its own test class for clarity and separation of concerns.
Base URL is passed dynamically using a system property (-DbaseUrl=http://...) so the same test suite can run in dev, QA, or prod without any code change.
Use RestAssured for readable and fluent API request syntax.
Validate not only status codes, but also response bodies, headers, and error cases.
We check:
Correct response code (e.g., 200, 404, 422).
Keys and values in the JSON response.
Error handling with invalid inputs.
In many cases (e.g., update, read, delete), you need a user to exist first.
So we create a user in the test and reuse that user ID for further steps.

## Test Scenarios
Each test class contains both positive and negative cases:
## Test Class       # Positive Scenario                    # Negative Scenario
CreateUser           TestValid name + email → expect 200    Invalid email format → expect 422
ReadUser             TestRead existing user → expect 200    Invalid user ID → expect 404
UpdateUser           TestUpdate valid user → expect 200     Update invalid user ID → expect 404 or 405
DeleteUser           TestDelete valid user → expect 200     Delete non-existing user → expect 404

## Reporting Strategy

We have used the Allure CLI for Clean, visual reports.
Captures test names, descriptions, steps, attachments.
Helps QA leads and developers quickly see failures and their root causes.

# How We Use Allure
# Annotate each test with:
@Epic / @Feature — for categorization
@Story — use case level
@Severity — importance of the test
@Step — break down reusable actions

## Maintainability & Scalability

BaseTest keeps setup logic in one place.
Utility methods like createUser() can be reused across multiple tests.

## CI/CD Integration Ready

Using Maven and TestNG makes it easy to plug into Jenkins, GitHub Actions, etc.
Allure CLI can be run as a build step to attach HTML reports to builds.

## Summary: Why This Approach Works

## Strength                    # Explanation
Clear separation of concerns   Each test does one thing well (Single Responsibility Principle).
Configurable environment       Tests can run on any environment by changing a flag.
Rich reporting                 Allure provides executive-level and developer-level insights.
Scalable structure             New test classes can be added without breaking existing ones.
Robust & reliable              Covers both positive and negative scenarios with assertion and chaining.


