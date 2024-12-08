FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} stadiumservice.jar

ENTRYPOINT ["java", "-jar", "/stadiumservice.jar"]

EXPOSE 9001