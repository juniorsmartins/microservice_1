package io.micronoticias.adapter.in.controller;

import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.adapter.out.repository.NoticiaRepository;
import io.micronoticias.config.exception.ApiError;
import io.micronoticias.config.exception.TipoDeErroEnum;
import io.micronoticias.util.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/noticias/insert-noticia.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/noticias/delete-noticia.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DisplayName("Notícia Controller - Criar")
class NoticiaControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/noticias";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Nested
    @DisplayName("Dados válidos")
    class DadoValido {

        @Test
        @DisplayName("completos por XML")
        void dadaNoticiaValida_QuandoCriarComContentNegotiationXML_EntaoRetornarHttp201() {

            var dtoIn = FabricaDeObjetosDeTeste.gerarNoticiaCriarDtoInBuilder().build();

            webTestClient.post()
                .uri(END_POINT)
                .accept(MediaType.APPLICATION_XML)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_XML)
                .expectBody().consumeWith(response -> {
                    assertThat(response.getResponseBody()).isNotNull();
                });
        }

        @Test
        @DisplayName("completos por JSON")
        void dadoNoticiaValida_QuandoCriarComContentNegotiationJSon_EntaoRetornarNoticiaCriarDtoOutComDadosIguaisEntradaAndHttp201() {

            var dtoIn = FabricaDeObjetosDeTeste.gerarNoticiaCriarDtoInBuilder().build();

            webTestClient.post()
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
        @DisplayName("completos persistidos")
        void dadaNoticiaValida_QuandoCriar_EntaoRetornarNoticiaCriarDtoOutComDadosPersistidos() {

            var dtoIn = FabricaDeObjetosDeTeste.gerarNoticiaCriarDtoInBuilder().build();

            var resposta = webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NoticiaCriarDtoOut.class)
                .returnResult().getResponseBody();

            var noticiaDoBanco = noticiaRepository.findById(resposta.id()).get();

            assertThat(noticiaDoBanco).isNotNull();
            assertThat(noticiaDoBanco.getId()).isNotNull();
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

    @Nested
    @DisplayName("Exceções")
    class NoticiaException {

        @Test
        @DisplayName("chapéu nulo")
        void dadoNoticiaInvalida_QuandoCriarComChapeuNulo_EntaoRetornarApiErrorAndHttp400() {

            var dtoIn = FabricaDeObjetosDeTeste.gerarNoticiaCriarDtoInBuilder()
                    .chapeu(null)
                    .build();

            webTestClient.post()
                .uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoIn)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ApiError.class)
                .consumeWith(response -> {
                    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
                    assertThat(response.getResponseBody().getClass()).isEqualTo(ApiError.class);
                    assertThat(response.getResponseBody().getTitle()).isEqualTo(TipoDeErroEnum.REQUISICAO_MAL_FORMULADA.getTitulo());
                });
        }
    }
}

