## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17: You can download and install it from [OpenJDK](https://openjdk.java.net/).
- Apache Maven 3.8: You can download and install it from the [official Maven website](https://maven.apache.org/download.cgi).
- Node.js
- 8080 port is free

## Getting Started

To get started with this project, follow these steps:

1. Clone this repository to your local machine:

   ```shell
   git clone git@git.epam.com:vasilisa_marachkouskaya/java-advanced-backend.git -b <branch>

2. Navigate to server folder and start it

   ```shell
   cd grpc-server
   mvn compile exec:java "-Dexec.mainClass=org.example.pingpong.GrpsServer"

3. Open second terminal window. Navigate to java client folder and start it

   ```shell
   cd grpc-java-client
   mvn compile exec:java "-Dexec.mainClass=org.example.pingpong.GrpsClient"

4. Open the third terminal window. Navigate to node client folder and start it

   ```shell
   cd grpc-node-client
   npm install
   node ./dynamic_codegen/greeter_client.js
