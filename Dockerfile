FROM openjdk:17
EXPOSE 8080
COPY target/franchise-0.0.1.jar /franchise-0.0.1.jar
ENTRYPOINT ["java", "-jar", "franchise-0.0.1.jar"]