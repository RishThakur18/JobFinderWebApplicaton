FROM openjdk:17
LABEL maintainer="rishthakur18@gmail.com"
ARG JAR_FILE
COPY $JAR_FILE jobify-backend-prod.jar
ENTRYPOINT ["java", "-Dserver.port=$PORT", "-jar", "/jobify-backend-prod"]