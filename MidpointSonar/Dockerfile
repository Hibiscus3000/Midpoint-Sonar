FROM eclipse-temurin:18-jdk-alpine as jar_builder
WORKDIR /opt/MidpointSonar
RUN apk update && apk upgrade && apk add git \
  && git clone https://github.com/Hibiscus3000/Midpoint-Sonar.git \
  && apk add maven && maven clean install

FROM eclipse-temurin:18-jre-alpine
WORKDIR /opt/MidpointSonar
COPY --from=jar_builder /opt/MidpointSonar/target/*.jar /opt/MidpointSonar/*.jar
EXPOSE 15000
ENTRYPOINT ["java", "-jar", "/opt/MidpointSonar/*.jar"]
