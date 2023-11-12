# Java 11 Spring Security Application with PostgreSQL Database
This is a simple Java 11 application that demonstrates the use of Spring Security features, including routing permissions, form-based authentication, storing users in a PostgreSQL database, and preventing brute force attacks. The application uses the following technologies:

- Java 11
- Spring Boot
- Spring Security
- PostgreSQL

## Prerequisites
Make sure you have the following installed on your machine:

- Java 11
- Maven
- PostgreSQL

## Database Setup
Create a PostgreSQL database named ***postgres***.
Update the application.properties file with your PostgreSQL database connection details:

```
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=your_username
   spring.datasource.password=your_password
```

## Getting Started

To get started with this project, follow these steps:

1. Clone this repository to your local machine:

   ```shell
   git clone git@git.epam.com:vasilisa_marachkouskaya/java-advanced-backend.git -b <branch>

2. Navigate to the project directory:

   ```shell
   cd your-java-project

3. Build the project using Maven:

   ```shell
   mvn clean install

4. Run spring boot application

## Testing
1. Open browser and navigate to http://localhost:8080/subscriptions/admin.
   You will be asked for credentials. Enter *admin/password*
2. Navigate to http://localhost:8080/subscriptions/info.
   You will be asked for credentials. Enter *user/password*
3. Navigate to http://localhost:8080/subscriptions/about.
4. Navigate to http://localhost:8080. Log in using one of the following credentials:
   - admin/password
   - user/password
   - full/password
5. Log out and enter user with not valid password at least 3 times. For instance enter *user/123*.
   Open http://localhost:8080/blocked.
   After 30 sec try to log in with valid user's credentials: *user/password*
6. Log out and check access to the following resources:
   - Navigate to http://localhost:8080/subscriptions/admin. You will be asked for credentials. Enter *admin/password*
   - Navigate to http://localhost:8080/subscriptions/info. You will be asked for credentials. Enter *user/password*
   - Navigate to http://localhost:8080/subscriptions/about.


