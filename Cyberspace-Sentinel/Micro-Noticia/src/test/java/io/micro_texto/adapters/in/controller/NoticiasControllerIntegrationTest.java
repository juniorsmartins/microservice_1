package io.micro_texto.adapters.in.controller;

import io.micro_texto.adapters.in.dto.response.NoticiaCriarDtoOut;
import io.micro_texto.adapters.out.repository.NoticiaRepository;
import io.micro_texto.config.exception.ApiError;
import io.micro_texto.utils.CriadorDeBuilders;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/noticias/noticias-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/noticias/noticias-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoticiasControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/noticias";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Test
    @Order(1)
    void criarNoticia_ComDadosValidos_RetornarNoticiaAndHttp201() {

        var dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder().build();

        this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(NoticiaCriarDtoOut.class)
            .consumeWith(response -> {
                assertThat(response.getResponseBody()).isNotNull();
                assertThat(response.getResponseBody().chapeu()).isEqualTo(dtoIn.chapeu());
                assertThat(response.getResponseBody().titulo()).isEqualTo(dtoIn.titulo());
                assertThat(response.getResponseBody().linhaFina()).isEqualTo(dtoIn.linhaFina());
                assertThat(response.getResponseBody().lide()).isEqualTo(dtoIn.lide());
                assertThat(response.getResponseBody().corpo()).isEqualTo(dtoIn.corpo());
                assertThat(response.getResponseBody().nomeAutor()).isEqualTo(dtoIn.nomeAutor());
                assertThat(response.getResponseBody().fonte()).isEqualTo(dtoIn.fonte());
                assertThat(response.getResponseBody().dataHoraCriacao()).isNotNull();
            });
    }

    @Test
    @Order(2)
    void criarNoticia_ComDadosValidos_RetornarNoticiaPersistida() {

        var dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder().build();

        var resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(NoticiaCriarDtoOut.class)
            .returnResult().getResponseBody();

        var noticiaDoBanco = this.noticiaRepository.findById(resposta.id()).get();

        assertThat(noticiaDoBanco).isNotNull();
        assertThat(dtoIn.chapeu()).isEqualTo(noticiaDoBanco.getChapeu());
        assertThat(dtoIn.titulo()).isEqualTo(noticiaDoBanco.getTitulo());
        assertThat(dtoIn.linhaFina()).isEqualTo(noticiaDoBanco.getLinhaFina());
        assertThat(dtoIn.lide()).isEqualTo(noticiaDoBanco.getLide());
        assertThat(dtoIn.corpo()).isEqualTo(noticiaDoBanco.getCorpo());
        assertThat(dtoIn.nomeAutor()).isEqualTo(noticiaDoBanco.getNomeAutor());
        assertThat(dtoIn.fonte()).isEqualTo(noticiaDoBanco.getFonte());
        assertThat(noticiaDoBanco.getDataHoraCriacao()).isNotNull();
    }

    @Test
    @Order(3)
    void criarNoticia_ComDadosInvalidos_RetornarApiErrorComHttpBadRequest() {

        var dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .chapeu(null)
            .build();

        var resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .chapeu("")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .chapeu(" ")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .chapeu("limite-máximo-de-20-caracteres-superado")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .titulo(null)
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .titulo("")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .titulo(" ")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .titulo("-----Limite-máximo-de-100-caracteres-ultrapassado-para-lançar-exceção-de-bean-validation-com-http-400-BadRequest.-----")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .linhaFina(null)
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .linhaFina("")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .linhaFina(" ")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .linhaFina("Limite-máximo-de-150-caracteres-superado-para-gerar-lançamento-de-exceção-de-bean-validation-com-httpStatus-400-BadRequest-e-ser-capturado-pelo-tratamento-global-de-exceção.")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .lide(null)
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .lide("")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .lide(" ")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .lide(CriadorDeBuilders.faker.lorem().characters(501, 600))
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .corpo(null)
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .corpo("")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .corpo(" ")
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .corpo(CriadorDeBuilders.faker.lorem().characters(5001, 6000))
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .nomeAutor(CriadorDeBuilders.faker.lorem().characters(51, 60))
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarNoticiaCriarDtoInBuilder()
            .fonte(CriadorDeBuilders.faker.lorem().characters(251, 300))
            .build();

        resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ApiError.class)
            .returnResult().getResponseBody();

        assertThat(resposta).isNotNull();
        assertThat(resposta.getStatus()).isEqualTo(400);
    }
}

