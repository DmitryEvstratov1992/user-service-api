FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/user-service-api-2.0.0.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]