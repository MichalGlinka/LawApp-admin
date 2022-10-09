FROM maven:3.6.3-openjdk-17-slim AS MAVEN_BUILD
COPY . /
RUN mvn package

FROM openjdk:17.0.2-slim-buster
EXPOSE 8081
COPY --from=MAVEN_BUILD LawApp-admin.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]