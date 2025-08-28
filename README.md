# QuestHub Lab – CS-460

## ✨ A Note About Copilot and AI

Copilot leads to 55% faster coding: https://github.com/features/copilot.  
You can use GitHub Copilot in this class for code suggestions, and there are 2 links you will need:

- Sign up for the **GitHub Student Developer Pack**: https://education.github.com/pack/join  
  (You’ll need to prove your student status using your school ID)
- After verification, activate **Copilot**: https://docs.github.com/en/copilot/quickstart
- Church guidance on AI: https://newsroom.churchofjesuschrist.org/article/church-jesus-christ-artificial-intelligence

---

## 🎯 Objective

You will build a Spring Boot microservices app called **QuestHub**.  
It includes 3 services and 1 API gateway. You will use **TDD** (Test-Driven Development) to write tests first, then build the logic.

---

## 💭 Thought for the Challenge

Microservices are like a team of missionaries—each with a specific role, but working together in unity. Tests are like spiritual preparation: they make us ready for service. TDD teaches us to **prepare before we act**, just like planning a talk, lesson, or temple trip.

---

## ⚙️ Requirements

- Java 21 (Configured in Codespaces)
- Spring Boot 3.2.x
- Maven 3.9.9
- JUnit 5
- Swagger/OpenAPI included in each service
- All code under 'edu.ensign.cs460.questhub'
- Each service in its own folder

---

## 🧭 Instructions

1. Build **Adventurer Service**: POST, GET, GET by ID
2. Build **Quest Service**: POST, GET, GET by ID
3. Build **Matching Service**: GET /match/{adventurerId}
4. Build **API Gateway** to route all services
5. Write tests first using JUnit and MockMvc/WebTestClient
6. Add Swagger for each service

---

## 📦 Deliverables

- All 4 services implemented
- Tests passing
- Gateway routing working
- Code pushed to GitHub Classroom repo

---

### 🧪 Test Your Code Locally

To check your score before pushing to GitHub:

```bash
mvn clean verify
```
This will compile your code and run all tests. If any tests fail, you will see errors in the terminal. Fix them before pushing!

You can also open the Test Explorer in VS Code to run tests one at a time.

---

## 📤 Submission

- Push your code to GitHub
- Wait for autograder to run
- Check points on GitHub
- Upload rubric to Canvas (if required)

---

## 🧪 Grading Criteria

- ✅ Project builds with correct dependencies
- ✅ Runs in GitHub Codespaces with no errors
- ✅ Passes all autograder tests
- ✅ README and rubric are complete and clear

---

## 📋 Rubric

| **Criteria**                               | **Excellent (Full Points)**                                                              | **Partial (Half Points)**                                              | **Incomplete (No Points)**                                    | **Points** |
| ------------------------------------------ | ---------------------------------------------------------------------------------------- | ---------------------------------------------------------------------- | ------------------------------------------------------------- | ---------- |
| **Adventurer Service Functionality (TDD)** | All endpoints implemented using TDD; all tests pass.                                     | Some endpoints implemented or some tests fail.                         | Service is missing or non-functional.                         | 20         |
| **Quest Service Functionality (TDD)**      | All endpoints implemented using TDD; all tests pass.                                     | Some endpoints implemented or some tests fail.                         | Service is missing or non-functional.                         | 20         |
| **Matching Service Logic**                 | Service correctly integrates with others, filtering logic works, and tests pass.         | Basic integration exists but filtering logic or test coverage is weak. | Matching service does not work or cannot call other services. | 20         |
| **API Gateway Configuration**              | All routes configured correctly and accessible via gateway.                              | Some routes missing or misconfigured.                                  | Gateway is missing or non-functional.                         | 15         |
| **Test Coverage & TDD Discipline**         | Tests written before implementation; test coverage is complete and readable.             | Tests exist but written after code or are missing key areas.           | No tests submitted or very poor test quality.                 | 15         |
| **Code Quality & Architecture**            | Code is clean, well-structured, uses SOLID principles, and is easy to read and maintain. | Minor issues in structure, naming, or modularity.                      | Code is disorganized, tightly coupled, or not readable.       | 10         |
  
Total Points: **100**
