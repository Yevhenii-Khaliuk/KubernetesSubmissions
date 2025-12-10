FROM eclipse-temurin:25 AS build

WORKDIR /opt/app
COPY . .
RUN ./gradlew bootJar


FROM eclipse-temurin:25-jre

WORKDIR /opt/app
COPY --from=build /opt/app/build/libs/ping-pong.jar .

CMD ["java", "-jar", "ping-pong.jar"]
