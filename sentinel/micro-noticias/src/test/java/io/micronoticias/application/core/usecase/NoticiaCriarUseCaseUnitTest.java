package io.micronoticias.application.core.usecase;

import io.micronoticias.adapter.out.NoticiaSalvarAdapter;
import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.util.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Notícia UseCase - Criar")
class NoticiaCriarUseCaseUnitTest {

    @MockBean
    private NoticiaSalvarAdapter salvarAdapter;

    @Autowired
    private NoticiaCriarUseCase criarUseCase;

    private NoticiaBusiness noticiaBusiness;

    @BeforeEach
    void criarCenario() {
        noticiaBusiness = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
    }

    @Nested
    @DisplayName("Dados válidos")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class DadoValido {

        @Test
        @Order(1)
        @DisplayName("completos")
        void dadoNoticiaValida_QuandoCriar_EntaoRetornarNoticiaCriadaComDadosIguaisAosDaEntrada() {
            noticiaBusiness.setId(2L);
            noticiaBusiness.setDataHoraCriacao(Instant.now());

            Mockito.when(salvarAdapter.salvar(Mockito.any(NoticiaBusiness.class))).thenReturn(noticiaBusiness);
            var resposta = criarUseCase.criar(noticiaBusiness);

            Assertions.assertAll("Asserções Criar",
                () -> Assertions.assertNotNull(resposta.getId()),
                () -> Assertions.assertEquals(noticiaBusiness.getChapeu(), resposta.getChapeu()),
                () -> Assertions.assertEquals(noticiaBusiness.getTitulo(), resposta.getTitulo()),
                () -> Assertions.assertEquals(noticiaBusiness.getLinhaFina(), resposta.getLinhaFina()),
                () -> Assertions.assertEquals(noticiaBusiness.getLide(), resposta.getLide()),
                () -> Assertions.assertEquals(noticiaBusiness.getCorpo(), resposta.getCorpo()),
                () -> Assertions.assertEquals(noticiaBusiness.getNomeAutor(), resposta.getNomeAutor()),
                () -> Assertions.assertEquals(noticiaBusiness.getFonte(), resposta.getFonte()),
                () -> Assertions.assertEquals(noticiaBusiness.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS),
                        resposta.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS))
            );
        }

        @Test
        @Order(2)
        @DisplayName("sem nome de autor")
        void dadoNoticiaValidaSemNomeAutor_QuandoCriar_EntaoRetornarNoticiaSalvaComDadosIguaisAosDaEntrada() {
            noticiaBusiness.setNomeAutor(null);

            Mockito.when(salvarAdapter.salvar(Mockito.any(NoticiaBusiness.class))).thenReturn(noticiaBusiness);
            var resposta = criarUseCase.criar(noticiaBusiness);

            Assertions.assertNull(resposta.getNomeAutor());
        }

        @Test
        @Order(3)
        @DisplayName("sem fonte")
        void dadoNoticiaValidaSemFonte_QuandoCriar_EntaoRetornarNoticiaSalvaComDadosIguaisAosDaEntrada() {
            noticiaBusiness.setFonte(null);

            Mockito.when(salvarAdapter.salvar(Mockito.any(NoticiaBusiness.class))).thenReturn(noticiaBusiness);
            var resposta = criarUseCase.criar(noticiaBusiness);

            Assertions.assertNull(resposta.getFonte());
        }
    }

    @Nested
    @DisplayName("Exceções")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NoticiaException {

        @Test
        @Order(1)
        @DisplayName("com notícia nula")
        void dadoNoticiaNula_QuandoCriar_EntaoLancarException() {
            Executable acao = () -> criarUseCase.criar(noticiaBusiness);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }
    }
}

