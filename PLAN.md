# PLAN.md

## Project: Quiz Management System

### Objective
Build a small production-ready Quiz Management System where an admin can create quizzes, and public users can take them and view results.

---

## Assumptions
1. Single admin user with default credentials:  
   - Username: `admin`  
   - Password: `******`
2. Public users do **not require login** to take quizzes.
3. Supported question types:
   - MCQ (single correct answer)
   - True/False
   - Text (short answer)
4. Quiz results are calculated as a **score**.
5. Questions are presented in **random order** when taking a quiz.
6. Frontend built using **React** (or Next.js), backend using **Spring Boot**, database using **MySQL**.

---

## Scope

### Must-Have
- Admin login with default credentials
- Admin can create quizzes with multiple questions
- Public users can take quizzes
- Display quiz results (score or correct answers)
- Minimal responsive frontend

### Nice-to-Have
- Edit/delete quizzes
- Multiple admins
- Analytics or user history
- Deployment to live URL

---

## High-Level Architecture
[React Frontend] <--REST API--> [Spring Boot Backend] <--JPA--> [MySQL Database]

**Backend Modules**
- AdminController – login, create quiz
- QuizController – fetch quiz, submit answers
- Services – AdminService, QuizService, ResultService
- Repositories – AdminRepo, QuizRepo, QuestionRepo, ResultRepo

**Frontend Pages**
1. Admin Login Page
2. Create Quiz Page
3. Public Quiz Page
4. Result Page

---

## Database Schema

**Admin Table**

| id | username | password |
|----|---------|----------|
| 1  | admin   |  ******  |

**Quiz Table**

| id | title |
|----|-------|
| 1  | Sample Quiz |

**Question Table**

| id | quiz_id | question_text           | type        | options (JSON)       | correct_answer |
|----|--------|------------------------|------------|--------------------|----------------|
| 1  | 1      | What is 2+2?           | MCQ        | ["2","3","4","5"]   | 4              |
| 2  | 1      | The sun rises in east?  | True/False | null                | true           |

**Result Table**

| id | quiz_id | score | user_answers (JSON) |

---

## API Endpoints

**Admin**
- `POST /admin/login` → `{username, password}` → returns token/session
- `POST /admin/quiz` → `{title, questions}` → create quiz

**Public**
- `GET /quiz/{id}` → fetch quiz (random questions)
- `POST /quiz/{id}/submit` → `{answers}` → returns score

---

## Implementation Plan
1. **Setup Spring Boot Backend**
   - Dependencies: Spring Web, Spring Data JPA, MySQL Driver, Lombok
2. **Create Entities & Repositories**
   - Admin, Quiz, Question, Result
3. **Service Layer**
   - AdminService – validate login
   - QuizService – create quiz, fetch quiz, calculate results
4. **Controllers**
   - AdminController – login, create quiz
   - QuizController – get quiz, submit answers
5. **Frontend**
   - Admin login page
   - Create quiz page (with random sample questions)
   - Public quiz page
   - Result page
6. **Testing**
   - Manual testing of all endpoints and pages
---
