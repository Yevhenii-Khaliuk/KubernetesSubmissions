FROM eclipse-temurin:25 AS jre-build

WORKDIR /opt/app
COPY . .
RUN javac LogGenerator.java

RUN $JAVA_HOME/bin/jlink \
    --add-modules java.base \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /javaruntime

FROM debian:13-slim
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"
COPY --from=jre-build /javaruntime $JAVA_HOME

WORKDIR /opt/app
COPY --from=jre-build /opt/app/LogGenerator.class .

CMD ["java", "LogGenerator"]
