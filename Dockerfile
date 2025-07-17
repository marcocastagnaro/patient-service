# Stage 1: build with Gradle (Debian-based)
FROM gradle:jdk17 AS build
WORKDIR /app

# copy your source and build the fat JAR
COPY --chown=gradle:gradle . /app
RUN gradle clean bootJar --no-daemon

# Stage 2: runtime on slim Debian
FROM openjdk:17-slim AS runtime
WORKDIR /app

# grab the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar ./app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
