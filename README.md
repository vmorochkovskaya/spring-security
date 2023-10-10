# Own data source spring boot starter

This is multi-module Java project, which is compatible with Java 17 and managed with Maven 3.8. 
It serves as a library for your Java application and covers Task 1.1-1.3 from Spring Foundation Module

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17: You can download and install it from [OpenJDK](https://openjdk.java.net/).
- Apache Maven 3.8: You can download and install it from the [official Maven website](https://maven.apache.org/download.cgi).

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

4. Run the demo-app project:

   ```shell
   java -jar demo-app/target/demo-app-0.0.1-SNAPSHOT.jar
   
5. Open Postman and send GET request to http://localhost:8080/actuator/. Ensure you use Basic Auth with admin/pass credentials

