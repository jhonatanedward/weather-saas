# Use OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

COPY build/libs/weather-subscription-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]