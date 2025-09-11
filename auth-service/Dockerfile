# Use OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

# Copia o jar buildado
COPY build/libs/auth-service-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta do Spring Boot
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "/app.jar"]