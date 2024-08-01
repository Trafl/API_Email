FROM bellsoft/liberica-openjdk-debian:17

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD  [ "java", "-jar", "demo-0.0.1-SNAPSHOT.jar" ]