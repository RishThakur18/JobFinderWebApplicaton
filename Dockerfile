FROM openjdk:17
LABEL maintainer="rishthakur18@gmail.com"
ADD ../../JobFinderWebApplicaton/backend-springboot/target/jobify-microservices-0.0.1-SNAPSHOT.jar backend-springboot.jar
ENTRYPOINT ["java","-jar", "/backend-springboot.jar"]