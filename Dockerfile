FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

# 1. Set the destination first
WORKDIR /usr/app

# 2. Source path is now just ./target/ because you are already in the app folder
COPY ./target/java-maven-app-*.jar .

# 3. Ensure the JAR name here matches the one in your target folder
ENTRYPOINT ["java", "-jar", "java-maven-app-1.1.0-SNAPSHOT.jar"]
