FROM maven:3-openjdk-17-slim AS build
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
COPY --from=build target/forwork.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]