FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests -Dquarkus.package.type=uber-jar

EXPOSE 8080

CMD ["java", "-jar", "target/catatan-uang-backend-1.0.0-SNAPSHOT-runner.jar"]
