# Spring Boot Application

## Table of Contents
- [Introduction](#introduction)
- [Technology Stack](#technology-stack)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Introduction
This is a Spring Boot application that serves as the backend for a frontend application. It utilizes Spring Boot 3.4.1, Java 21, and PostgreSQL for database management.

## Technology Stack
- **Java**: Version 21
- **Spring Boot**: Version 3.4.1
- **PostgreSQL**: Used as the relational database management system
- **Maven**: For dependency management and build automation

## Setup and Installation
To set up and run the application locally, follow these steps:

1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:
    ```bash
    cd <project-directory>
    ```

3. Configure the PostgreSQL database:
    - Ensure PostgreSQL is installed and running.
    - Create a new database for the application.
    - Update the `application.properties` or `application.yml` file with your PostgreSQL database credentials.

4. Build the project using Maven:
    ```bash
    ./mvnw clean install
    ```

## Running the Application
To run the application, use the following command:
```bash
./mvnw spring-boot:run