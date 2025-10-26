FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/maintenance_request_service-0.0.1-SNAPSHOT.jar maintenance-request.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "maintenance-request.jar"]