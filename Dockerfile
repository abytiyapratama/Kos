FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/catatan-uang-backend-1.0.0-SNAPSHOT-runner.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
