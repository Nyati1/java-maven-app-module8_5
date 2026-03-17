FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

# When building from the repository root as build context, the module jar lives under java-maven-app/target
COPY ./java-maven-app/target/java-maven-app-*.jar /usr/app/
WORKDIR /usr/app

# Use shell form so a wildcard jar name will be resolved at container start
CMD ["sh", "-c", "java -jar java-maven-app-*.jar"]
