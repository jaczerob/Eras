FROM maven:3.9-eclipse-temurin-21-alpine as build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /home/app/target/eras.jar /usr/local/lib/eras.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/eras.jar"]