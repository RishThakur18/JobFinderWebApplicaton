FROM openjdk:17
LABEL maintainer="rishthakur18@gmail.com"
COPY build/libs/*.jar jobify-backend-prod.jar
ENTRYPOINT ["java", "-jar", "/jobify-backend-prod"]