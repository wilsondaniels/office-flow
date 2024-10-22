# Dockerfile
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/office-flow-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["sh", "-c", "sleep 5; java -jar app.jar"]