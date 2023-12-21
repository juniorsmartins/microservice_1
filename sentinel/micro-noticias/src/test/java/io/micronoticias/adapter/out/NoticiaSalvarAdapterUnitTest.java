package io.micronoticias.adapter.out;

import io.micronoticias.adapter.out.entity.NoticiaEntity;
import io.micronoticias.adapter.out.repository.NoticiaRepository;
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
@DisplayName("Notícia Adapter - Salvar")
class NoticiaSalvarAdapterUnitTest {

    @MockBean
    private NoticiaRepository repository;

    @Autowired
    private NoticiaSalvarAdapter salvarAdapter;

    private NoticiaBusiness noticiaBusiness;

    private NoticiaEntity noticiaEntity;

    @BeforeEach
    void criarCenario() {
        noticiaBusiness = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();

        noticiaEntity = NoticiaEntity.builder()
            .id(2L)
            .chapeu(noticiaBusiness.getChapeu())
            .titulo(noticiaBusiness.getTitulo())
            .linhaFina(noticiaBusiness.getLinhaFina())
            .lide(noticiaBusiness.getLide())
            .corpo(noticiaBusiness.getCorpo())
            .nomeAutor(noticiaBusiness.getNomeAutor())
            .fonte(noticiaBusiness.getFonte())
            .dataHoraCriacao(Instant.now())
            .build();
    }

    @Nested
    @DisplayName("Dados válidos")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class DadoValido {

        @Test
        @Order(1)
        @DisplayName("completos")
        void dadoNoticiaValida_QuandoSalvar_EntaoRetornarNoticiaSalvaComDadosIguaisAosDaEntrada() {
            Mockito.when(repository.save(Mockito.any(NoticiaEntity.class))).thenReturn(noticiaEntity);
            var resposta = salvarAdapter.salvar(noticiaBusiness);

            Assertions.assertAll("Asserções - Salvar Adapter",
                () -> Assertions.assertNotNull(resposta.getId()),
                () -> Assertions.assertEquals(noticiaBusiness.getChapeu(), resposta.getChapeu()),
                () -> Assertions.assertEquals(noticiaBusiness.getTitulo(), resposta.getTitulo()),
                () -> Assertions.assertEquals(noticiaBusiness.getLinhaFina(), resposta.getLinhaFina()),
                () -> Assertions.assertEquals(noticiaBusiness.getLide(), resposta.getLide()),
                () -> Assertions.assertEquals(noticiaBusiness.getCorpo(), resposta.getCorpo()),
                () -> Assertions.assertEquals(noticiaBusiness.getNomeAutor(), resposta.getNomeAutor()),
                () -> Assertions.assertEquals(noticiaBusiness.getFonte(), resposta.getFonte()),
                () -> Assertions.assertEquals(noticiaEntity.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS),
                        resposta.getDataHoraCriacao().truncatedTo(ChronoUnit.SECONDS))
            );
        }

        @Test
        @Order(2)
        @DisplayName("sem nome de autor")
        void dadoNoticiaValidaSemNomeAutor_QuandoSalvar_EntaoRetornarNoticiaSalvaComDadosIguaisAosDaEntrada() {
            noticiaBusiness.setNomeAutor(null);
            noticiaEntity.setNomeAutor(null);

            Mockito.when(repository.save(Mockito.any(NoticiaEntity.class))).thenReturn(noticiaEntity);
            var resposta = salvarAdapter.salvar(noticiaBusiness);

            Assertions.assertNull(resposta.getNomeAutor());
        }

        @Test
        @Order(3)
        @DisplayName("sem fonte")
        void dadoNoticiaValidaSemFonte_QuandoSalvar_EntaoRetornarNoticiaSalvaComDadosIguaisAosDaEntrada() {
            noticiaBusiness.setFonte(null);
            noticiaEntity.setFonte(null);

            Mockito.when(repository.save(Mockito.any(NoticiaEntity.class))).thenReturn(noticiaEntity);
            var resposta = salvarAdapter.salvar(noticiaBusiness);

            Assertions.assertNull(resposta.getFonte());
        }
    }

    @Nested
    @DisplayName("Exceções")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NoticiaException {

        @Test
        @Order(1)
        @DisplayName("Com notícia nula")
        void dadoNoticiaNula_QuandoSalvar_EntaoLancarNullPointerException() {
            Executable acao = () -> salvarAdapter.salvar(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }
    }
}

