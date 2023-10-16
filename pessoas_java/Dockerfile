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

ENV PORT 8101
EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "pessoas.jar"]

