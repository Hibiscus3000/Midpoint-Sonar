FROM eclipse-temurin:19-jdk-alpine as jar_builder

WORKDIR /app

RUN apk update && apk add curl && apk add git && apk add maven \
  && git clone https://github.com/Hibiscus3000/Midpoint-Sonar.git
WORKDIR Midpoint-Sonar/MidpointSonar
RUN mvn clean install

EXPOSE 15000

ENTRYPOINT ["mvn", "spring-boot:run"]
