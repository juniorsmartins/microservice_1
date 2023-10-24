FROM amazoncorretto:21.0.0-al2023 AS build-stage

ARG DIRETORIO_TRABALHO=pessoas
WORKDIR /$DIRETORIO_TRABALHO

# Copiando apenas o Maven Wrapper e pom.xml inicialmente
COPY ./.mvn ./.mvn
COPY ./mvnw ./
COPY ./pom.xml ./

# Baixando dependências
RUN ./mvnw dependency:go-offline

# Agora copiamos o código-fonte e construímos o projeto
COPY ./src ./src
RUN ./mvnw clean package -DskipTests


FROM amazoncorretto:21.0.0-al2023-headless AS production-stage

ARG DIRETORIO_TRABALHO=pessoas
WORKDIR /$DIRETORIO_TRABALHO

COPY --from=build-stage /$DIRETORIO_TRABALHO/target/*.jar pessoas.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

ENV PORT 8101
EXPOSE $PORT

ENTRYPOINT ["./wait-for-it.sh", "postgres_pessoas:5432", "--", "java", "-jar", "pessoas.jar"]

