package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.adapters.in.dto.request.PessoaCadastrarDtoIn;
import io.pessoas_java.adapters.in.dto.response.PessoaCadastrarDtoOut;
import io.pessoas_java.configs.AbstractIntegrationTest;
import io.pessoas_java.configs.TestConfigs;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CorsIntegrationJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;

    private static ObjectMapper objectMapper;

    private static PessoaCadastrarDtoIn pessoaCadastrarDtoIn;

    private static PessoaCadastrarDtoOut pessoaDtoOut;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        pessoaCadastrarDtoIn = PessoaCadastrarDtoIn.builder()
            .nome("Richard")
            .sobrenome("Stallman")
            .cpf("53028054051")
            .sexo("Male")
            .genero("FG")
            .dataNascimento("23/09/1991")
            .nivelEducacional("Mestre")
            .nacionalidade("brasileiro")
            .build();
    }

    @Test
    @Order(1)
    @DisplayName("Cors - Post 201")
    public void testeCreate() throws IOException {

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_TESTE_POSITIVO)
            .setBasePath("/api/v1/pessoas")
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = RestAssured.given()
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(pessoaCadastrarDtoIn)
            .when()
                .post()
            .then()
                .statusCode(201)
            .extract()
                .body()
                    .asString();

        var pessoaDeSaida = objectMapper.readValue(content, PessoaCadastrarDtoOut.class);
        pessoaDtoOut = pessoaDeSaida;

        Assertions.assertNotNull(pessoaDeSaida.getKey());
        Assertions.assertEquals(pessoaCadastrarDtoIn.nome(), pessoaDeSaida.getNome());
        Assertions.assertEquals(pessoaCadastrarDtoIn.sobrenome(), pessoaDeSaida.getSobrenome());
        Assertions.assertEquals(pessoaCadastrarDtoIn.cpf(), pessoaDeSaida.getCpf());
        Assertions.assertEquals(pessoaCadastrarDtoIn.sexo(), pessoaDeSaida.getSexo());
        Assertions.assertEquals(pessoaCadastrarDtoIn.genero(), pessoaDeSaida.getGenero());
        Assertions.assertEquals(pessoaCadastrarDtoIn.dataNascimento(), pessoaDeSaida.getDataNascimento());
        Assertions.assertEquals(pessoaCadastrarDtoIn.nivelEducacional(), pessoaDeSaida.getNivelEducacional());
        Assertions.assertEquals(pessoaCadastrarDtoIn.nacionalidade(), pessoaDeSaida.getNacionalidade());
    }

    @Test
    @Order(2)
    @DisplayName("Cors - Post 403")
    public void testeCreateException() {

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_TESTE_NEGATIVO)
            .setBasePath("/api/v1/pessoas")
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = RestAssured.given()
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(pessoaCadastrarDtoIn)
            .when()
                .post()
            .then()
                .statusCode(403)
            .extract()
                .body()
                    .asString();

        Assertions.assertNotNull(content);
        Assertions.assertEquals("Invalid CORS request", content);
    }

    @Test
    @Order(3)
    @DisplayName("Cors - Get 200")
    public void testeConsultarPorChave() throws IOException {

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_TESTE_POSITIVO)
            .setBasePath("/api/v1/pessoas")
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = RestAssured.given()
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("chave", pessoaDtoOut.getKey())
            .when()
                .get("{chave}")
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();

        var pessoaDeSaida = objectMapper.readValue(content, PessoaCadastrarDtoOut.class);

        Assertions.assertEquals(pessoaDtoOut.getKey(), pessoaDeSaida.getKey());
        Assertions.assertEquals(pessoaDtoOut.getNome(), pessoaDeSaida.getNome());
        Assertions.assertEquals(pessoaDtoOut.getSobrenome(), pessoaDeSaida.getSobrenome());
        Assertions.assertEquals(pessoaDtoOut.getCpf(), pessoaDeSaida.getCpf());
        Assertions.assertEquals(pessoaDtoOut.getSexo(), pessoaDeSaida.getSexo());
        Assertions.assertEquals(pessoaDtoOut.getGenero(), pessoaDeSaida.getGenero());
        Assertions.assertEquals(pessoaDtoOut.getDataNascimento(), pessoaDeSaida.getDataNascimento());
        Assertions.assertEquals(pessoaDtoOut.getNivelEducacional(), pessoaDeSaida.getNivelEducacional());
        Assertions.assertEquals(pessoaDtoOut.getNacionalidade(), pessoaDeSaida.getNacionalidade());
    }

    @Test
    @Order(4)
    @DisplayName("Cors - Get 403")
    public void testeConsultarPorChaveException() {

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_TESTE_NEGATIVO)
            .setBasePath("/api/v1/pessoas")
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = RestAssured.given()
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("chave", pessoaDtoOut.getKey())
            .when()
                .get("{chave}")
            .then()
              .statusCode(403)
            .extract()
                .body()
                    .asString();

        Assertions.assertNotNull(content);
        Assertions.assertEquals("Invalid CORS request", content);
    }
}

