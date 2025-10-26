FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/worker-service-0.0.1-SNAPSHOT.jar worker-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "worker-service.jar"]