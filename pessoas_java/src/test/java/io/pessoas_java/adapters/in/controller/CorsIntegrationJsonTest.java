package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.adapters.in.dto.request.PessoaDtoIn;
import io.pessoas_java.configs.AbstractIntegrationTest;
import io.pessoas_java.configs.TestConfigs;
import io.pessoas_java.dtos.PessoaDtoOut;
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

    private static PessoaDtoIn pessoaDtoIn;

    private static PessoaDtoOut pessoaDtoOut;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        pessoaDtoIn = PessoaDtoIn.builder()
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
    @DisplayName("Cors - Post")
    public void testeCreate() throws IOException {

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "https://teste.com.br")
            .setBasePath("/api/v1/pessoas")
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var content = RestAssured.given()
            .spec(specification)
            .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(pessoaDtoIn)
                .when()
                .post()
            .then()
                .statusCode(201)
            .extract()
                .body()
                    .asString();

        var pessoaDeSaida = objectMapper.readValue(content, PessoaDtoOut.class);
        pessoaDtoOut = pessoaDeSaida;

        Assertions.assertNotNull(pessoaDeSaida.getChave());
        Assertions.assertEquals(pessoaDtoIn.nome(), pessoaDeSaida.getNome());
        Assertions.assertEquals(pessoaDtoIn.sobrenome(), pessoaDeSaida.getSobrenome());
        Assertions.assertEquals(pessoaDtoIn.cpf(), pessoaDeSaida.getCpf());
        Assertions.assertEquals(pessoaDtoIn.sexo(), pessoaDeSaida.getSexo());
        Assertions.assertEquals(pessoaDtoIn.genero(), pessoaDeSaida.getGenero());
        Assertions.assertEquals(pessoaDtoIn.dataNascimento(), pessoaDeSaida.getDataNascimento());
        Assertions.assertEquals(pessoaDtoIn.nivelEducacional(), pessoaDeSaida.getNivelEducacional());
        Assertions.assertEquals(pessoaDtoIn.nacionalidade(), pessoaDeSaida.getNacionalidade());
    }
}

