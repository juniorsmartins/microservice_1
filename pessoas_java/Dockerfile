# Esta é a primeira fase da construção da imagem
FROM amazoncorretto:21.0.0-al2023 AS build-stage

# Define a variável de ambiente DIRETORIO_TRABALHO com o valor padrão 'pessoas', que, neste caso, define o nome do diretório de trabalho.
ARG DIRETORIO_TRABALHO=pessoas
WORKDIR /$DIRETORIO_TRABALHO

# Copia o Maven Wrapper e o pom.xml para o diretório de trabalho
COPY ./.mvn ./.mvn
COPY ./mvnw ./
COPY ./pom.xml ./

# Baixa as dependências do Maven (pom.xml) para o cache
RUN ./mvnw dependency:go-offline

# Copia o código-fonte para o diretório de trabalho
COPY ./src ./src
# Constrói o projeto usando o Maven e gera o arquivo JAR
RUN ./mvnw clean package -DskipTests



# Esta é a segunda fase da construção da imagem
FROM amazoncorretto:21.0.0-al2023-headless AS production-stage

# Define a variável de ambiente DIRETORIO_TRABALHO com o valor padrão 'pessoas', que, neste caso, define o nome do diretório de trabalho.
ARG DIRETORIO_TRABALHO=pessoas
WORKDIR /$DIRETORIO_TRABALHO

# Copia o arquivo JAR gerado na fase de construção anterior para a imagem
COPY --from=build-stage /$DIRETORIO_TRABALHO/target/*.jar pessoas.jar
# Copia o script 'wait-for-it.sh' para a imagem (O script wait-for-it.sh é utilizado para aguardar a disponibilidade do banco de dados PostgreSQL antes de iniciar o aplicativo Java.)
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

# Define a variável de ambiente PORT com o valor 8101
ENV PORT 8101
# Expõe a porta definida para fora da imagem
EXPOSE $PORT

# Define o ponto de entrada do contêiner
ENTRYPOINT ["./wait-for-it.sh", "postgres_pessoas:5432", "--", "java", "-jar", "pessoas.jar"]

