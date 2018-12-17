FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/8xx-config-1.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/app.jar"]