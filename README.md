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

## 🚀 Quick Start

1. Open this repository in GitHub Codespaces or a compatible devcontainer.
2. The container includes **Java 21** and **Maven 3.9.9**.
3. Run all tests with:
   ```bash
   mvn clean verify
   ```
4. Start an individual service locally, for example:
   ```bash
   cd adventurer-service
   mvn spring-boot:run
   ```
   The service will launch on the default Spring Boot port (8080).
5. Use the VS Code Test Explorer or `mvn test` to rerun tests as you work.

---

## QuestHub Lab — Build Your Own Microservices

### Service Requirements

#### 1. Adventurer Service
Purpose: Manage adventurers in the realm (e.g., players or characters).
Package: `edu.ensign.cs460.questhub.adventurer`
Data Model: `Adventurer`

```java
public class Adventurer {
    private Long id;
    private String name;
    private int level;
    private String characterClass; // e.g., "Warrior", "Wizard", "Rogue"
}
```

Required Endpoints

| Method | URL | Description |
| ------ | --- | ----------- |
| POST | `/adventurers` | Create a new adventurer |
| GET | `/adventurers` | List all adventurers |
| GET | `/adventurers/{id}` | Get adventurer by ID |

Behavior Rules

- `id` is auto-generated (you can use an in-memory list or `AtomicLong`)
- `name` must not be blank
- `level` must be >=1
- `characterClass` must be one of: `"Warrior"`, `"Wizard"`, `"Rogue"`

#### 2. Quest Service
Purpose: Manage a list of quests available in the realm.
Package: `edu.ensign.cs460.questhub.quest`
Data Model: `Quest`

```java
public class Quest {
    private Long id;
    private String title;
    private int difficulty;  // e.g., 1–10
    private int reward;      // gold, XP, etc.
    private String description;
    private String allowedClass; // e.g., "Warrior", "Wizard", or "All"
}
```

Required Endpoints

| Method | URL | Description |
| ------ | --- | ----------- |
| POST | `/quests` | Create a new quest |
| GET | `/quests` | List all quests |
| GET | `/quests/{id}` | Get a quest by ID |

Behavior Rules

- `title` and `description` are required
- `difficulty` must be >=1
- `allowedClass` must be `"Warrior"`, `"Wizard"`, `"Rogue"`, or `"All"`

#### 3. Matching Service
Purpose: Recommend quests for an adventurer based on their level and class.
Package: `edu.ensign.cs460.questhub.matching`

Required Endpoints

| Method | URL | Description |
| ------ | --- | ----------- |
| GET | `/match/{adventurerId}` | Returns a list of quests matched to the adventurer’s level and class |

Matching Logic

- Fetch adventurer by calling adventurer-service
- Fetch all quests quest-service
- Filter quests:
  - `quest.difficulty <= adventurer.level`
  - `quest.allowedClass == adventurer.characterClass || quest.allowedClass == "All"`

Response

Returns a JSON array of matched `Quest` objects.

#### 4. API Gateway
Purpose: Route incoming requests to the appropriate service.
Package: `edu.ensign.cs460.questhub.gateway`

Required Routes (set in `application.yml` for Spring Cloud Gateway):

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: adventurer-service
          uri: http://localhost:8081
          predicates:
            - Path=/api/adventurers/**
        - id: quest-service
          uri: http://localhost:8082
          predicates:
            - Path=/api/quests/**
        - id: matching-service
          uri: http://localhost:8083
          predicates:
            - Path=/api/match/**
```

Students may use localhost ports for local dev, or logical URLs in Codespaces as appropriate.

### Example Data

Adventurer

```json
{
  "name": "Thorin",
  "level": 5,
  "characterClass": "Warrior"
}
```

Quest

```json
{
  "title": "Defend the Outpost",
  "difficulty": 4,
  "reward": 250,
  "description": "Protect the town from bandits.",
  "allowedClass": "Warrior"
}
```

Matching Output (for Adventurer ID 1)

```json
[
  {
    "id": 2,
    "title": "Defend the Outpost",
    "difficulty": 4,
    "reward": 250,
    "description": "Protect the town from bandits.",
    "allowedClass": "Warrior"
  }
]
```

### Recap: What You Must Build

| Service | Endpoints | Tests Required | Points |
| ------- | --------- | -------------- | ------ |
| Adventurer Service | POST / GET / GET by ID | Yes | 20 |
| Quest Service | POST / GET / GET by ID | Yes | 20 |
| Matching Service | GET /match/{adventurerId} | Yes | 20 |
| API Gateway | Route all /api/** paths | Yes | 15 |
| Test Coverage & TDD | JUnit 5, written before code | Yes | 15 |
| Code Quality | SOLID principles, good structure | No (manual) | 10 |

---

## 🧭 Instructions

### Overview
In this lab, you will create a set of Spring Boot microservices. These services will work together like a small team. Each service will do one job.

You will:
- Build 3 services:
  - Adventurer Service
  - Quest Service
  - Matching Service
- Add 1 API Gateway to connect them
- Write tests first, then write code (TDD = Test-Driven Development)
- Use GitHub Codespaces and VS Code
- You are building a system like a game backend. It helps “adventurers” find quests that match their skills.

### What to Do First
1. Accept the GitHub Classroom link from your teacher.
2. Open your repository in GitHub Codespaces.
3. Wait for the environment to finish setting up (Java 21 and Maven will be ready).
4. In Codespaces, open the README.md and follow the instructions below step by step.

### Step-by-Step Instructions

#### Step 1: Adventurer Service (20 pts)
Location: `adventurer-service/src`
Model class: `Adventurer` (fields: id, name, level, characterClass)

You must:
- Write tests for these endpoints first:
  - `POST /adventurers` → Add a new adventurer
  - `GET /adventurers/{id}` → Get one adventurer
  - `GET /adventurers` → List all adventurers
- After you finish writing the tests, write the controller, service, and model
- Use `@RestController`, `@Service`, and a simple in-memory list (e.g., `List<Adventurer>`)
- Use `@WebMvcTest` for controller tests
This step checks off the first row in your rubric.

#### Step 2: Quest Service (20 pts)
Location: `quest-service/src`
Model class: `Quest` (fields: id, title, difficulty, reward, description)

You must:
- Write tests for these endpoints first:
  - `POST /quests` → Add a new quest
  - `GET /quests` → List all quests
  - `GET /quests/{id}` → Get one quest
- After writing the tests, implement the controller, service, and model
This step checks the second row in your rubric.

#### Step 3: Matching Service (20 pts)
Location: `matching-service/src`

You must:
- Create one endpoint: `GET /match/{adventurerId}`
- This service:
  - Calls Adventurer Service to get the adventurer
  - Calls Quest Service to get all quests
  - Filters quests that match the adventurer's level and class
    - Example: A level 5 warrior should not see a level 10 wizard quest
- Write tests for this matching logic using `@SpringBootTest`
This step checks the third row in your rubric.

#### Step 4: API Gateway (15 pts)
Location: `api-gateway/src`
Use Spring Cloud Gateway to route requests:
- `/api/adventurers/**` → Adventurer Service
- `/api/quests/**` → Quest Service
- `/api/match/**` → Matching Service

You must:
- Set up routes in `application.yml`
- Test that each service is reachable from the gateway
This step checks the fourth row in your rubric.

#### Step 5: Testing and TDD (15 pts)
- Write tests first (before writing logic) for each controller
- Use JUnit 5 and MockMvc or WebTestClient
- Run your tests often using the Test Explorer in VS Code
- Make sure all tests pass
This step checks the fifth row in your rubric.

#### Step 6: Code Quality (10 pts)
Your code must:
- Use clean class names, methods, and variables
- Follow SOLID principles
- Organize packages inside `edu.ensign.cs460.questhub`
- Use `@Service`, `@RestController`, and interfaces when possible
This step checks the last row in your rubric.

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

- Push your code to GitHub using Codespaces
- Make sure your tests pass — the autograder will check
- Submit the GitHub Classroom repo link in Canvas for this assignment

---

## 🌟 Final Encouragement

You are not just writing code — you are building something with discipline and purpose. Just like Nephi built a ship he had never seen before, you are learning to build systems that serve others. Start small, test often, and move forward with faith.

> "By small and simple things are great things brought to pass." — Alma 37:6

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
