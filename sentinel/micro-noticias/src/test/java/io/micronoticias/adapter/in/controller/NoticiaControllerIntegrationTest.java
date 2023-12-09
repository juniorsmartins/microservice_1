package io.micronoticias.adapter.in.controller;

import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.adapter.out.repository.NoticiaRepository;
import io.micronoticias.util.CriadorDeBuilders;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/noticias/insert-noticia.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/noticias/delete-noticia.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoticiaControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/noticias";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Test
    @Order(1)
    void criarNoticia_ComDadosValidos_RetornarNoticiaDtoOutAndHttp201() {

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
}

