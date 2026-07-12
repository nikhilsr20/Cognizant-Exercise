# 🔍 SonarQube Integration with Spring Boot

> A practical demonstration of integrating **SonarQube**, **JaCoCo**, and **JUnit 5** with a Spring Boot application to perform static code analysis, improve code quality, and monitor test coverage.

---

## 🚀 Overview

This project showcases how to analyze a Spring Boot application using SonarQube and continuously improve its quality by identifying bugs, security hotspots, code smells, and insufficient test coverage.

The application is based on a **Vehicle Purchase Management System**, where SonarQube analysis is performed on business logic and unit tests to ensure maintainable and production-ready code.

---

## ✨ Features

- Static Code Analysis
- Code Smell Detection
- Bug Detection
- Security Hotspots
- JaCoCo Test Coverage
- Maven Sonar Scanner
- JUnit 5 Testing
- Quality Gate Validation
- Local Docker-based SonarQube Setup

---

# 🏗 Project Structure

```text
sonarqube-demo
│
├── SpringBootDemo
│   ├── src
│   ├── pom.xml
│   ├── sonar-project.properties
│
├── images
│
├── notes
│
└── README.md
```

---

# ⚙️ Technology Stack

| Technology | Purpose |
|------------|----------|
| Java 21 | Programming Language |
| Spring Boot 3 | Backend Framework |
| Maven | Build Tool |
| SonarQube | Static Code Analysis |
| JaCoCo | Code Coverage |
| Docker | SonarQube Deployment |
| JUnit 5 | Unit Testing |

---

# 📌 Application Modules

The sample application simulates a vehicle purchase workflow.

### Business Functionalities

- Vehicle Purchase Profile
- Loan Calculation
- Down Payment Estimation
- EMI Calculation
- Premium Vehicle Eligibility
- Budget Recommendation
- Purchase Reference Generation

---

# 🔄 Workflow

```text
Developer
     │
     ▼
Run Maven Tests
     │
     ▼
Generate JaCoCo Coverage
     │
     ▼
Execute Sonar Scanner
     │
     ▼
SonarQube Analysis
     │
     ▼
Quality Gate Evaluation
```

---

# 📊 Analysis Process

## 1️⃣ Local SonarQube Server

SonarQube Community Edition runs locally inside Docker.

<p align="center">
<img src="images/02-docker-sonarqube-container-running.png" width="900">
</p>

---

## 2️⃣ Code Analysis

The project is scanned using Maven Sonar Scanner.

✔ Bugs

✔ Vulnerabilities

✔ Code Smells

✔ Duplications

✔ Coverage

<p align="center">
<img src="images/11-maven-sonar-analysis-success.png" width="900">
</p>

---

## 3️⃣ Initial Report

The first scan intentionally contains quality issues to demonstrate SonarQube reporting capabilities.

<p align="center">
<img src="images/04-project-overview-failed-low-coverage.png" width="900">
</p>

---

## 4️⃣ Improvements

The following improvements were applied:

- Replaced insecure implementations
- Removed duplicated code
- Fixed String comparison issues
- Eliminated unused code
- Improved exception handling
- Increased unit test coverage
- Added parameterized test cases

---

## 5️⃣ Final Result

### Test Summary

| Metric | Result |
|---------|--------|
| Tests Executed | 43 |
| Failures | 0 |
| Errors | 0 |
| Coverage | 93.8% |
| Bugs | 0 |
| Vulnerabilities | 0 |
| Duplications | 0% |

<p align="center">
<img src="images/13-project-overview-quality-gate-passed.png" width="900">
</p>

---

# 💻 Useful Commands

### Run Tests

```bash
./mvnw clean verify
```

### Run Sonar Analysis

```bash
mvn clean verify sonar:sonar \
-Dsonar.projectKey=SpringBootDemo \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=<YOUR_TOKEN>
```

### Start SonarQube

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:lts-community
```

---

# 📈 Learning Outcomes

By completing this project you will learn:

- Integrating SonarQube with Spring Boot
- Measuring Code Coverage using JaCoCo
- Writing Effective Unit Tests
- Understanding Quality Gates
- Identifying Security Hotspots
- Improving Maintainability
- Building Production-Ready Java Applications

---

# ⭐ Highlights

- Spring Boot 3
- SonarQube Community Edition
- Docker Integration
- JaCoCo Coverage
- JUnit 5
- Maven Sonar Scanner
- Quality Gate Validation