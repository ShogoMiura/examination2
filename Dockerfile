FROM amazoncorretto:17 AS build
COPY . .
RUN ./mvnw clean package -Dmaven.test.skip=true
FROM amazoncorretto:17-alpine
COPY --from=build target/examination2-0.0.1-SNAPSHOT.jar examination2-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "examination2-0.0.1-SNAPSHOT.jar"]
