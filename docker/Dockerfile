FROM  maven:3.8.3-openjdk-17
COPY target/personalized-data-service-0.0.1-SNAPSHOT.jar /usr/app/myapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/myapp.jar"]
