package io.micronoticias.adapter.in.controller;

import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.application.port.in.NoticiaCriarInputPort;
import io.micronoticias.util.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
class NoticiaCriarControllerUnitTest {

    @MockBean
    private NoticiaCriarInputPort criarInputPort;

    @Autowired
    private NoticiaController controller;

    @Test
    void criarNoticia_ComDadosValidos_RetornarNoticiaCriarDtoOutAndHttp201() {
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

        var resposta = this.controller.criar(dtoIn);

        Assertions.assertAll("Asserções Criar Controller",
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

