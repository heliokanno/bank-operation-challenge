FROM openjdk:17-jdk-slim-buster
RUN mkdir /app

COPY build/libs/bank-operation-challenge-*.jar /app/challenge-bank-api.jar

WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/challenge-bank-api.jar"]
