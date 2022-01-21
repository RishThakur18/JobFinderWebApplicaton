FROM openjdk:17
LABEL maintainer="rishthakur18@gmail.com"
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} jobify-backend-prod.jar
ENTRYPOINT ["java", "-jar", "/jobify-backend-prod"]