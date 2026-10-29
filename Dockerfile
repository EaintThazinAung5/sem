FROM eclipse-temurin:26-jre

COPY ./target/devops.jar /tmp/devops.jar

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "devops.jar", "db:3306", "30000"]
