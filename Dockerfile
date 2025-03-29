# Use the official OpenJDK image
FROM openjdk:23

# Copy the built JAR file into the container
COPY target/courseApp-1.0-SNAPSHOT.jar app.jar

# Run the JAR file when the container starts
CMD ["java", "-jar", "app.jar"]
