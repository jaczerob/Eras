FROM openjdk:17
VOLUME /tmp
COPY target/eras.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]