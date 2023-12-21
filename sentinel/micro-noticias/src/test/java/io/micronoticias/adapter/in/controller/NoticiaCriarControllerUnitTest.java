package io.micronoticias.adapter.in.controller;

import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.port.in.NoticiaCriarInputPort;
import io.micronoticias.util.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Notícia Controller - Criar")
class NoticiaCriarControllerUnitTest {

    @MockBean
    private NoticiaCriarInputPort criarInputPort;

    @Autowired
    private NoticiaController controller;

    @Nested
    @DisplayName("Dados válidos")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class DadosValidos {

        @Test
        @Order(1)
        @DisplayName("completos")
        void dadoNoticiaValida_QuandoCriar_EntaoRetornarNoticiaCriadaDtoOutAndHttp201() {
            var dtoIn = FabricaDeObjetosDeTeste.gerarNoticiaCriarDtoInBuilder().build();

            var noticiaBusiness = new NoticiaBusiness();
            noticiaBusiness.setId(2L);
            noticiaBusiness.setChapeu(dtoIn.chapeu());
            noticiaBusiness.setTitulo(dtoIn.titulo());
            noticiaBusiness.setLinhaFina(dtoIn.linhaFina());
            noticiaBusiness.setLide(dtoIn.lide());
            noticiaBusiness.setCorpo(dtoIn.corpo());
            noticiaBusiness.setNomeAutor(dtoIn.nomeAutor());
            noticiaBusiness.setFonte(dtoIn.fonte());
            noticiaBusiness.setDataHoraCriacao(Instant.now());

            Mockito.when(criarInputPort.criar(Mockito.any())).thenReturn(noticiaBusiness);

            var resposta = controller.criar(dtoIn);

            Assertions.assertAll("Asserções Criar",
                () -> Assertions.assertEquals(ResponseEntity.class, resposta.getClass()),
                () -> Assertions.assertEquals(NoticiaCriarDtoOut.class, resposta.getBody().getClass()),
                () -> Assertions.assertEquals(HttpStatus.CREATED, resposta.getStatusCode()),

                () -> Assertions.assertNotNull(resposta.getBody().id()),
                () -> Assertions.assertEquals(dtoIn.chapeu(), resposta.getBody().chapeu()),
                () -> Assertions.assertEquals(dtoIn.titulo(), resposta.getBody().titulo()),
                () -> Assertions.assertEquals(dtoIn.linhaFina(), resposta.getBody().linhaFina()),
                () -> Assertions.assertEquals(dtoIn.lide(), resposta.getBody().lide()),
                () -> Assertions.assertEquals(dtoIn.corpo(), resposta.getBody().corpo()),
                () -> Assertions.assertEquals(dtoIn.nomeAutor(), resposta.getBody().nomeAutor()),
                () -> Assertions.assertEquals(dtoIn.fonte(), resposta.getBody().fonte()),
                () -> Assertions.assertEquals(noticiaBusiness.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS),
                        resposta.getBody().dataHoraCriacao().truncatedTo(ChronoUnit.SECONDS))
            );
        }
    }

    @Nested
    @DisplayName("Exceções")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NoticiaException {

        @Test
        @Order(1)
        @DisplayName("com notícia nula")
        void dadoNoticiaNula_QuandoSalvar_EntaoLancarException() {
            Executable acao = () -> controller.criar(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }
    }
}

