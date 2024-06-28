FROM eclipse-temurin:17.0.10_7-jre
LABEL authors="rlaredo"

ARG JAR_FILE=ach-api/target/ach-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ach-api-0.0.1-SNAPSHOT.jar
EXPOSE 8081

ENTRYPOINT ["java","-Duser.language=es","-Duser.region=BO","-Duser.country=BO","-Duser.timezone=America/La_Paz", "-jar","/ach-api-0.0.1-SNAPSHOT.jar"]
