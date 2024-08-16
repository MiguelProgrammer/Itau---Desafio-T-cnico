FROM openjdk:17-jdk-alpine
LABEL authors="Miguel Silva - Desafio Itau"
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/geradornotafiscal-0.0.1-SNAPSHOT.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app/geradornotafiscal-0.0.1-SNAPSHOT.jar"]