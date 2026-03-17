FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

COPY ./java-maven-app/target/java-maven-app-*.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "java-maven-app-1.0-SNAPSHOT.jar"]
