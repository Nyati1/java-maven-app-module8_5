FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

COPY ./java-maven-app/target/java-maven-app-*.jar /usr/app/
WORKDIR /usr/app

# Use shell so the wildcard can be expanded to the actual jar filename produced by the module build
ENTRYPOINT ["sh", "-c", "java -jar java-maven-app-*.jar"]
