# Use the base OpenJDK 21 image
FROM openjdk:21-jdk

# Copy the JAR file into the container at /app
COPY build/libs/healthCheck-0.0.1-SNAPSHOT.jar app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
#server
#CMD ["java","-jar","-Dspring.profiles.active=prod","-Djasypt.encryptor.password=Popcon","docker-springboot.jar"]



# Define the entry point for the container
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "docker-springboot.jar"]