# Esta é a primeira fase da construção da imagem
FROM amazoncorretto:21-alpine3.18-full AS build-stage

ARG CYBERSPACE_SENTINEL=Cyberspace-Sentinel
ARG MICRO_NOTICIA=Micro-Noticia

# Esta linha define o diretório de trabalho para o contêiner Docker como /app. É aqui que quaisquer comandos
# subsequentes serão executados no contêiner.
WORKDIR /$CYBERSPACE_SENTINEL/$MICRO_NOTICIA

# Captura o pom pai
COPY ./pom.xml ./$CYBERSPACE_SENTINEL
# Esta linha copia o diretório ./.mvn da máquina host para o diretório /app no contêiner.
COPY ./$MICRO_NOTICIA/.mvn ./.mvn
# Esta linha copia o arquivo executável mvnw da máquina host para o diretório /app no contêiner. Este é o script de
# wrapper Maven que permite executar comandos Maven sem ter que instalar o Maven globalmente na máquina host.
COPY ./$MICRO_NOTICIA/mvnw ./
# Esta linha copia o arquivo POM Maven do projeto (pom.xml) da máquina host para o diretório /app no contêiner. Este
# arquivo contém todas as dependências e informações de configuração do projeto.
COPY ./$MICRO_NOTICIA/pom.xml ./

# Esta linha executa o comando Maven ./mvnw dependency:resolve no contêiner. Este comando resolve todas as
# dependências especificadas no arquivo POM do projeto e as baixa para o repositório Maven local do contêiner.
# RUN ./mvnw dependency:resolve

# Baixa as dependências do Maven (pom.xml) para o cache
RUN ./mvnw dependency:go-offline

# Esta linha copia o diretório de código-fonte do projeto (./src) da máquina host para o diretório /app no contêiner.
COPY ./$MICRO_NOTICIA/src ./src

# Constrói o projeto usando o Maven e gera o arquivo JAR
RUN ./mvnw clean install -DskipTests

# Esta linha define o comando a ser executado quando o contêiner Docker iniciar. Especifica a execução do comando
# Maven ./mvnw spring-boot:run, que iniciará o aplicativo Spring Boot no contêiner.
# CMD ["./mvnw", "spring-boot:run"]

# Esta é a segunda fase da construção da imagem
FROM amazoncorretto:21.0.1-alpine3.18 AS production-stage

ARG CYBERSPACE_SENTINEL=Cyberspace-Sentinel
ARG MICRO_NOTICIA=Micro-Noticia
WORKDIR /$CYBERSPACE-SENTINEL/$MICRO-NOTICIA

# Copia o arquivo JAR gerado na fase de construção anterior para a imagem
COPY --from=build-stage /$CYBERSPACE_SENTINEL/$MICRO_NOTICIA/target/*.jar noticias.jar

# Copia o script 'wait-for-it.sh' para a imagem (O script wait-for-it.sh é utilizado para aguardar a disponibilidade do banco de dados PostgreSQL antes de iniciar o aplicativo Java.)
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

# Define a variável de ambiente PORT com o valor 8101
ENV PORT 8101
# Expõe a porta definida para fora da imagem
EXPOSE $PORT

# Define o ponto de entrada do contêiner
ENTRYPOINT ["./wait-for-it.sh", "banco:8080", "--", "java", "-jar", "noticias.jar"]

