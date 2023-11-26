package io.micro_texto.adapters.in.controller;

import io.micro_texto.adapters.in.dto.response.NoticiaCriarDtoOut;
import io.micro_texto.adapters.out.repository.NoticiaRepository;
import io.micro_texto.utils.CriadorDeBuilders;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

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

        var resposta = this.webTestClient.post()
            .uri(END_POINT)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(NoticiaCriarDtoOut.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(dtoIn.chapeu()).isEqualTo(resposta.chapeu());
        org.assertj.core.api.Assertions.assertThat(dtoIn.titulo()).isEqualTo(resposta.titulo());
        org.assertj.core.api.Assertions.assertThat(dtoIn.linhaFina()).isEqualTo(resposta.linhaFina());
        org.assertj.core.api.Assertions.assertThat(dtoIn.lide()).isEqualTo(resposta.lide());
        org.assertj.core.api.Assertions.assertThat(dtoIn.corpo()).isEqualTo(resposta.corpo());
        org.assertj.core.api.Assertions.assertThat(dtoIn.nomeAutor()).isEqualTo(resposta.nomeAutor());
        org.assertj.core.api.Assertions.assertThat(dtoIn.fonte()).isEqualTo(resposta.fonte());
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

        org.assertj.core.api.Assertions.assertThat(noticiaDoBanco).isNotNull();
        org.assertj.core.api.Assertions.assertThat(dtoIn.chapeu()).isEqualTo(noticiaDoBanco.getChapeu());
        org.assertj.core.api.Assertions.assertThat(dtoIn.titulo()).isEqualTo(noticiaDoBanco.getTitulo());
        org.assertj.core.api.Assertions.assertThat(dtoIn.linhaFina()).isEqualTo(noticiaDoBanco.getLinhaFina());
        org.assertj.core.api.Assertions.assertThat(dtoIn.lide()).isEqualTo(noticiaDoBanco.getLide());
        org.assertj.core.api.Assertions.assertThat(dtoIn.corpo()).isEqualTo(noticiaDoBanco.getCorpo());
        org.assertj.core.api.Assertions.assertThat(dtoIn.nomeAutor()).isEqualTo(noticiaDoBanco.getNomeAutor());
        org.assertj.core.api.Assertions.assertThat(dtoIn.fonte()).isEqualTo(noticiaDoBanco.getFonte());
    }
}

