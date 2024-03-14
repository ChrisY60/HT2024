FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /target/AcademiX-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
CMD ["java", "-jar", "app.jar", "-Dspring.profiles.active=dockerembbed,oauth-security"]