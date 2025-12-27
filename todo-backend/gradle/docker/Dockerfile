FROM eclipse-temurin:25 AS build

WORKDIR /opt/app
COPY . .
RUN ./gradlew bootJar


FROM eclipse-temurin:25-jre

WORKDIR /opt/app
COPY --from=build /opt/app/build/libs/todo-app.jar .

RUN mkdir images
ENV IMAGES_DIR_PATH=/opt/app/images

CMD ["java", "-jar", "todo-app.jar"]
