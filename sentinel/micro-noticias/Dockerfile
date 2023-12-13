FROM amazoncorretto:21.0.1-alpine3.18 AS builder

WORKDIR /app

COPY ./target/micro-noticias-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

CMD ["java", "-jar", "micro-noticias-0.0.1-SNAPSHOT.jar"]

