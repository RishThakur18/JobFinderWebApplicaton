FROM openjdk:17
LABEL maintainer="rishthakur18@gmail.com"
ADD build/libs/jobify-microservices-0.0.1-SNAPSHOT.jar jobify-backend-prod.jar
ENTRYPOINT ["java","-jar", "/jobify-backend-prod"]