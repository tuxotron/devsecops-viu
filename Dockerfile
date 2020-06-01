FROM maven:3.6.3-jdk-11 AS build

COPY src /app/src
COPY pom.xml /app/
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11
COPY --from=build /app/target/DevSecOps-0.0.1-SNAPSHOT.jar /app/app.jar
COPY contrast/contrast_security.yaml /etc/contrast/java/contrast_security.yaml
COPY contrast/contrast.jar /app/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-Dcontrast.agent.java.standalone_app_name=DevSecOpsDemo", "-Dcontrast.server.name=DockerServer", "-javaagent:contrast.jar","-jar","app.jar"]