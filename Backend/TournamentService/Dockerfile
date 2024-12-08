FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} tournamentservice.jar

ENTRYPOINT ["java", "-jar", "/tournamentservice.jar"]

EXPOSE 9001