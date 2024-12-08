FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} bookingservice.jar

ENTRYPOINT ["java", "-jar", "/bookingservice.jar"]

EXPOSE 9001