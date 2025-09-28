# Build the application
FROM gradle:jdk21-alpine AS builder

WORKDIR /app

# Copy the Gradle build files first to cache dependencies
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ gradle/

# Make gradlew executable
RUN chmod +x gradlew

RUN apk add libc6-compat

COPY src ./src

# Do clean build only if source code or build.gradle changes
RUN ./gradlew clean build  --no-daemon

# Set up the runner image
FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder ./app/build/libs/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port
EXPOSE 4000

# GRPC server port
EXPOSE 9595

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL authors="Aniket"
