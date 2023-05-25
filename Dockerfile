FROM openjdk:19-jdk-alpine
MAINTAINER JoviSimons
COPY target/AuthService-0.0.1-SNAPSHOT.jar AuthService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/AuthService-0.0.1-SNAPSHOT.jar"]