# Use the official Maven image with Java 21
FROM maven:3.9.8-eclipse-temurin-21

# Set the working directory
WORKDIR /app

# Copy the Maven configuration
COPY pom.xml .

# Copy the application code
COPY src ./src

# Install dependencies offline
RUN mvn dependency:go-offline

# Expose the application's port
EXPOSE 8080

# Start the application with Maven in development mode
CMD ["mvn", "spring-boot:run"]
