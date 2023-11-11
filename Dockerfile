FROM adoptopenjdk/openjdk17:alpine-jre
WORKDIR /app
COPY target/fxdeal-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]