    FROM eclipse-temurin:19-jdk-alpine as jar_builder
WORKDIR /opt/
RUN apk update && apk upgrade && apk add git && apk add maven \
  && git clone https://github.com/Hibiscus3000/Midpoint-Sonar.git
WORKDIR /opt/Midpoint-Sonar/MidpointSonar
RUN mvn clean install

FROM eclipse-temurin:19-jre-alpine
WORKDIR /opt/Midpoint-Sonar/MidpointSonar
COPY --from=jar_builder /opt/Midpoint-Sonar/MidpointSonar/target/*.jar /opt/Midpoint-Sonar/MidpointSonar/*.jar
EXPOSE 15000
VOLUME /midpoint_report
ENTRYPOINT ["java", "-jar", "/opt/MidpointSonar/*.jar"]
