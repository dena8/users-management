# Use the OpenJDK image as the base image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file to the container
COPY target/users-management-0.0.1-SNAPSHOT.jar ./users-management-0.0.1-SNAPSHOT.jar

# Expose the port that the Spring Boot application listens on (change the port if needed)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "users-management-0.0.1-SNAPSHOT.jar"]
