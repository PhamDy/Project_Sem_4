FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} orderservice.jar

ENTRYPOINT ["java", "-jar", "/orderservice.jar"]

EXPOSE 9008