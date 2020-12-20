## Image for dev

FROM maven:3-jdk-8-alpine as builder
ARG MAVEN_OPTS
WORKDIR /app
COPY pom.xml .
# Allow maven dependencies caching until the pom changes
RUN mvn dependency:go-offline
COPY src ./src/
RUN mvn package

## Image for prod

FROM openjdk:8-alpine
WORKDIR /app
COPY --from=builder /app/target/*.?ar .
CMD java -Xdebug -Xrunjdwp:transport=dt_socket,address=4321,server=y,suspend=n -jar $(ls *.?ar)