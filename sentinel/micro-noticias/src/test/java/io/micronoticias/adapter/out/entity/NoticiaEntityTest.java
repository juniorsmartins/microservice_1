package io.micronoticias.adapter.out.entity;

import io.micronoticias.application.core.domain.NoticiaBusiness;
import io.micronoticias.util.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Classe NotíciaEntity")
class NoticiaEntityTest {

    private NoticiaEntity noticiaEntity;

    @BeforeEach
    void criarCenario() {
        noticiaEntity = FabricaDeObjetosDeTeste.gerarNoticiaEntityBuilder().build();
    }

    @Nested
    @DisplayName("Métodos Padrão")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class MetodosPadrao {

        @Test
        @Order(1)
        @DisplayName("toString")
        void dadoNoticiaValida_QuandoToString_EntaoRetornarStringIgual() {
            var resposta = "NoticiaEntity(" +
                    "id=" + noticiaEntity.getId() +
                    ", chapeu=" + noticiaEntity.getChapeu() +
                    ", titulo=" + noticiaEntity.getTitulo() +
                    ", linhaFina=" + noticiaEntity.getLinhaFina() +
                    ", lide=" + noticiaEntity.getLide() +
                    ", corpo=" + noticiaEntity.getCorpo() +
                    ", nomeAutor=" + noticiaEntity.getNomeAutor() +
                    ", fonte=" + noticiaEntity.getFonte() +
                    ", dataHoraCriacao=" + noticiaEntity.getDataHoraCriacao() +
                    ", dataHoraAtualizacao=" + noticiaEntity.getDataHoraAtualizacao() +
                    ')';
            Assertions.assertEquals(resposta, noticiaEntity.toString());
        }

        @Test
        @Order(2)
        @DisplayName("equals")
        void dadoDuasNoticiasValidas_QuandoEquals_EntaoRetornarNotEquals() {
            var primeiraNoticia = FabricaDeObjetosDeTeste.gerarNoticiaEntityBuilder().build();
            var segundaNoticia = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
            Assertions.assertNotEquals(primeiraNoticia, segundaNoticia);
        }

        @Test
        @Order(3)
        @DisplayName("hashCode")
        void dadoDoisHashCodeValidos_QuandoHashCode_EntaoRetornarNotEquals() {
            var primeiraNoticia = FabricaDeObjetosDeTeste.gerarNoticiaEntityBuilder().build();
            var segundaNoticia = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();

            var primeiroHash = primeiraNoticia.hashCode();
            var segundoHash = segundaNoticia.hashCode();

            Assertions.assertNotEquals(primeiroHash, segundoHash);
        }
    }
}

