FROM openjdk:11-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} springrediscacheapplication.jar
ENTRYPOINT ["java","-jar","/springrediscacheapplication.jar"]