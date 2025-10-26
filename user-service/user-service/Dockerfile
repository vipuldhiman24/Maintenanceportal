#Use official OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the built jar file (replace with your actual jar name if different)
COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "user-service.jar"]