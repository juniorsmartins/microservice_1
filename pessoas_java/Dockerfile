FROM amazoncorretto:21.0.0-al2023 AS BUILDER

ARG DIRETORIO_TRABALHO=pessoas
WORKDIR /$DIRETORIO_TRABALHO

COPY ./.mvn ./.mvn
COPY ./mvnw ./
COPY ./pom.xml ./
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean package -DskipTests


FROM amazoncorretto:21.0.0-al2023

ARG DIRETORIO_TRABALHO=pessoas
WORKDIR /$DIRETORIO_TRABALHO

COPY --from=builder /$DIRETORIO_TRABALHO/target/*.jar pessoas.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

ENV PORT 8101
EXPOSE $PORT

ENTRYPOINT ["./wait-for-it.sh", "postgres_pessoas:5432", "--", "java", "-jar", "pessoas.jar"]

