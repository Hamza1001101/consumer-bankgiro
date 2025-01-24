FROM eclipse-temurin:23-jdk-alpine as builder

WORKDIR /app


COPY . /app
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:23-jdk-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar consumer-app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "consumer-app.jar"]