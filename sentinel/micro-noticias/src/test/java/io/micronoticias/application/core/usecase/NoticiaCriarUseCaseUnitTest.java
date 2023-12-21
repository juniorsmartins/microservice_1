package io.micronoticias.application.core.usecase;

import io.micronoticias.adapter.out.NoticiaSalvarAdapter;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.util.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Notícia UseCase")
class NoticiaCriarUseCaseUnitTest {

    @MockBean
    private NoticiaSalvarAdapter salvarAdapter;

    @Autowired
    private NoticiaCriarUseCase criarUseCase;

    @Nested
    @DisplayName("Método Criar")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CriarTest {

        @Test
        @Order(1)
        @DisplayName("Com Notícia válida")
        void dadoNoticiaValida_QuandoCriar_EntaoRetornarNoticiaCriadaComDadosIguaisAosDaEntrada() {
            var business = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
            business.setId(2L);
            business.setDataHoraCriacao(Instant.now());

            Mockito.when(salvarAdapter.salvar(Mockito.any(NoticiaBusiness.class))).thenReturn(business);

            var resposta = criarUseCase.criar(business);

            Assertions.assertAll("Asserções Criar UseCase",
                () -> Assertions.assertNotNull(resposta.getId()),
                () -> Assertions.assertEquals(business.getChapeu(), resposta.getChapeu()),
                () -> Assertions.assertEquals(business.getTitulo(), resposta.getTitulo()),
                () -> Assertions.assertEquals(business.getLinhaFina(), resposta.getLinhaFina()),
                () -> Assertions.assertEquals(business.getLide(), resposta.getLide()),
                () -> Assertions.assertEquals(business.getCorpo(), resposta.getCorpo()),
                () -> Assertions.assertEquals(business.getNomeAutor(), resposta.getNomeAutor()),
                () -> Assertions.assertEquals(business.getFonte(), resposta.getFonte()),
                () -> Assertions.assertEquals(business.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS),
                        resposta.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS))
            );
        }
    }



//    @Test
//    @Order(2)
//    void criarNoticia_ComDadosInvalidos_RetornarExceptions() {

//        final var business = new NoticiaBusiness(1L, null, "Título", "Linha Fina", "Lide", "Corpo",
//                "NomeAutor", "Fonte", Instant.now());
//
//        Mockito.when(salvarAdapter.salvar(Mockito.any(NoticiaBusiness.class))).thenReturn(business);
//
//        Assertions.assertThrows(CampoNuloProibidoException.class, () -> this.criarUseCase.criar(business));


//        final var business2 = new NoticiaBusiness(2L, "Chapéu", null, "Linha Fina", "Lide", "Corpo",
//                "NomeAutor", "Fonte", Instant.now());
//
//        Mockito.when(salvarAdapter.salvar(Mockito.any(NoticiaBusiness.class))).thenReturn(business2);
//
//        Assertions.assertThrows(CampoVazioProibidoException.class, () -> this.criarUseCase.criar(business2));


//    }
}

