# Rule Engine with AST

## Overview
This project implements a Rule Engine using Abstract Syntax Trees (AST). The engine allows the creation, combination, and evaluation of rules based on user-defined conditions. It uses Spring Boot, MySQL, and JPA to store and process rules dynamically.

## Features
- Create and evaluate rules using an AST structure.
- Combine rules with logical operators (AND/OR).
- Evaluate conditions such as age, department, salary, etc.
- Store rules in MySQL.

## Technologies Used
- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **MySQL**
- **Lombok** (for reducing boilerplate code)

## How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/khanshaif9102/Rule-Engine-with-AST.git
2. Set up MySQL and create a database called rule_engine_db.

3. Update the application.properties file located in src/main/resources with your MySQL credentials:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/rule_engine_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. Run the application:
   ```
   mvn spring-boot:run
   ```
5. The application will be available at http://localhost:8080
   
# API Endpoints
## Create Rule:
- POST /api/rules/create
- Request Body: Raw rule string (e.g., "(age > 30 AND department = 'Sales')")
- Response: AST representation of the rule
## Combine Rules:
- POST /api/rules/combine
- Request Body: Two rules and an operator (AND/OR)
- Response: Combined rule AST
## Evaluate Rule:
- POST /api/rules/evaluate
- Request Body: Rule and user data (e.g., age, department)
- Response: True/False based on rule evaluation
# Future Improvements
- Add support for more complex logical operators and data types.
- Create a user interface to manage rules more easily.
