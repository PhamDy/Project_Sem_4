FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} configservice.jar

ENTRYPOINT ["java", "-jar", "/configservice.jar"]

EXPOSE 9296