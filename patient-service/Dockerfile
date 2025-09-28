# Build the application
FROM gradle:jdk21-alpine AS builder

WORKDIR /app

# Copy the Gradle build file first (caching depencdencies)
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ gradle/

# Make gradlew executable
RUN chmod +x gradlew

RUN apk add libc6-compat

# Copy source code after dependencies are cached
COPY src ./src

#Run gradle build after the source code is copied
RUN ./gradlew clean build

#Set up the runner image
FROM openjdk:21-jdk AS runner

WORKDIR /app

#Copy the JAR file from the builder stage
COPY --from=builder ./app/build/libs/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

#Expose port
EXPOSE 4000

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL authors="Aniket"

