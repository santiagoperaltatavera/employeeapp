# EmployeeApp

EmployeeApp is a Spring Boot application designed to manage employee data through REST and SOAP APIs. It uses MySQL as the database and supports environment variable configuration for flexibility.

---

## Features

-   **REST API**: Provides endpoint for creating an employee.
-   **SOAP API**: Allows employee creation via SOAP services.
-   **Environment Configuration**: Uses `.env` file for database and application settings.
-   **OpenAPI Documentation**: Integrated with SpringDoc for API documentation.
-   **Database Integration**: Supports MySQL and Hibernate for data persistence.

---

## Requirements

-   **Java**: OpenJDK 21
-   **Database**: MySQL
-   **Build Tool**: Gradle
-   **IDE**: IntelliJ IDEA (recommended)

---

## Setup Instructions

1.  Clone the repository:
    ```bash
    git clone <https://github.com/santiagoperaltatavera/employeeapp.git>
    cd employeeapp
    ```

2.  Create a `.env` file in the root directory with the following content:
    ```
    DATABASE_URL=jdbc:mysql://localhost:3306/localdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
    DATABASE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
    DATABASE_USERNAME=root
    DATABASE_PASSWORD=rootkey
    JPA_HIBERNATE_DDL_AUTO=update
    JPA_SHOW_SQL=true
    HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect
    H2_CONSOLE_ENABLED=false
    SERVER_PORT=8080
    ```

3.  Build the application:
    ```bash
    ./gradlew clean build
    ```

4.  Run the application:
    ```bash
    ./gradlew bootRun
    ```

---

## Access the application

-   **REST API**: `http://localhost:8080/employees`

---

## REST API Endpoints

### Create Employee

-   **Method**: `GET`
-   **URL**: `/employees`
-   **Parameters**:
    -   `firstName` (String)
    -   `lastName` (String)
    -   `documentType` (String)
    -   `documentNumber` (String)
    -   `dateOfBirth` (String, format: `YYYY-MM-DD`)
    -   `hireDate` (String, format: `YYYY-MM-DD`)
    -   `position` (String)
    -   `salary` (Double)

**Example Request**

```bash
curl -X GET "http://localhost:8080/employees?firstName=John&lastName=Doe&documentType=ID&documentNumber=12345&dateOfBirth=1990-01-01&hireDate=2023-01-01&position=Developer&salary=50000"
