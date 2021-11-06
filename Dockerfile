FROM openjdk:11-jre-slim
RUN mkdir -p /app
ADD build/libs/app-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "/app/app-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=dev"]