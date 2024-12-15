FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} commonservice.jar

ENTRYPOINT ["java", "-jar", "/commonservice.jar"]

EXPOSE 9094