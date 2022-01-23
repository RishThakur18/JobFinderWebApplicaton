#ARG PROJECT_DIRECTORY=/jobify-backend
#
#FROM gradle:jdk17 as build-image
#ARG PROJECT_DIRECTORY
#ENV APP_HOME=$PROJECT_DIRECTORY
#WORKDIR $APP_HOME
#COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts $APP_HOME/
#COPY --chown=gradle:gradle src $APP_HOME/src
#CMD gradle --no-daemon build
#
#FROM openjdk:17
#ARG PROJECT_DIRECTORY
#ENV APP_HOME=$PROJECT_DIRECTORY
#COPY --from=build-image $APP_HOME/build/libs/jobify-backend-0.0.1-SNAPSHOT.jar jobify-backend-prod-image.jar
#ENTRYPOINT ["java", "-jar", "/jobify-backend-prod-image"]

FROM openjdk:17
LABEL maintainer="rishthakur18@gmail.com"
COPY build/libs/*.jar jobify-backend-prod.jar
ENTRYPOINT ["java", "-jar", "/jobify-backend-prod"]