# Stage 1: Build the JAR file (using a multi-stage build for efficiency)
FROM maven:3.8.7-jdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime image
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/java-maven-app-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
