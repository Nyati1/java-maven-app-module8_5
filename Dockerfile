FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

# 1. Set the directory first
WORKDIR /usr/app

# 2. Copy the JAR into the current WORKDIR (.)
:
COPY ./java-maven-app/target/java-maven-app-*.jar .

ENTRYPOINT ["java", "-jar", "java-maven-app-1.0-SNAPSHOT.jar"]
