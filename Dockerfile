# Stage 1: Build
FROM maven:latest AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:25-jre-alpine
COPY --from=builder build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]