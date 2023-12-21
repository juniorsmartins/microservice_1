package io.micronoticias.application.core.domain;

import com.github.javafaker.Faker;
import io.micronoticias.config.exception.http_400.CampoNuloProibidoException;
import io.micronoticias.config.exception.http_400.CampoVazioProibidoException;
import io.micronoticias.config.exception.http_400.DadoComTamanhoMaximoInvalidoException;
import io.micronoticias.util.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Classe NotíciaBusiness")
class NoticiaBusinessUnitTest {

    public static Faker faker = new Faker();

    private NoticiaBusiness noticiaBusiness;

    @BeforeEach
    void criarCenario() {
        noticiaBusiness = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
    }

    @Nested
    @DisplayName("Chapéu")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Chapeu {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoChapeuNulo_QuandoSetChapeu_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setChapeu(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoChapeuVazioOuEmBranco_QuandoSetChapeu_EntaoLancarException(String chapeu) {
            Executable acao = () -> noticiaBusiness.setChapeu(chapeu);
            Assertions.assertThrows(CampoVazioProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"21_caracteres--------", "30_caracteres-----------------"})
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoChapeuComTamanhoInvalido_QuandoSetChapeu_EntaoLancarException(String chapeu) {
            Executable acao = () -> noticiaBusiness.setChapeu(chapeu);
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Título")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Titulo {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoTituloNulo_QuandoSetTitulo_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setTitulo(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoTituloVazioOuEmBranco_QuandoSetTitulo_EntaoLancarException(String titulo) {
            Executable acao = () -> noticiaBusiness.setTitulo(titulo);
            Assertions.assertThrows(CampoVazioProibidoException.class, acao);
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoTituloComTamanhoInvalido_QuandoSetTitulo_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setTitulo(faker.lorem().characters(101, 150));
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Linha Fina")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class LinhaFina {

        @Test
        @Order(1)
        @DisplayName("nula")
        void dadoLinhaFinaNula_QuandoSetLinhaFina_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setLinhaFina(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazia ou em branco")
        void dadoLinhaFinaVaziaOuEmBranco_QuandoSetLinhaFina_EntaoLancarException(String linhaFina) {
            Executable acao = () -> noticiaBusiness.setLinhaFina(linhaFina);
            Assertions.assertThrows(CampoVazioProibidoException.class, acao);
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoLinhaFinaComTamanhoInvalido_QuandoSetLinhaFina_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setLinhaFina(faker.lorem().characters(151, 200));
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Lide")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Lide {

        @Test
        @Order(1)
        @DisplayName("nula")
        void dadoLideNulo_QuandoSetLide_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setLide(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoLideVazioOuEmBranco_QuandoSetLide_EntaoLancarException(String lide) {
            Executable acao = () -> noticiaBusiness.setLide(lide);
            Assertions.assertThrows(CampoVazioProibidoException.class, acao);
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoLideComTamanhoInvalido_QuandoSetLide_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setLide(faker.lorem().characters(501, 600));
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Corpo")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Corpo {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoCorpoNulo_QuandoSetCorpo_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setCorpo(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoCorpoVazioOuEmBranco_QuandoSetCorpo_EntaoLancarException(String corpo) {
            Executable acao = () -> noticiaBusiness.setCorpo(corpo);
            Assertions.assertThrows(CampoVazioProibidoException.class, acao);
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoCorpoComTamanhoInvalido_QuandoSetCorpo_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setCorpo(faker.lorem().characters(5001, 5100));
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Nome Autor")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NomeAutor {

        @Test
        @Order(1)
        @DisplayName("com tamanho inválido")
        void dadoNomeAutorComTamanhoInvalido_QuandoSetNomeAutor_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setNomeAutor(faker.lorem().characters(51, 75));
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Fonte")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Fonte {

        @Test
        @Order(1)
        @DisplayName("com tamanho inválido")
        void dadoFonteComTamanhoInvalido_QuandoSetFonte_EntaoLancarException() {
            Executable acao = () -> noticiaBusiness.setFonte(faker.lorem().characters(251, 275));
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Métodos Padrão")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class MetodosPadrao {

        @Test
        @Order(1)
        @DisplayName("toString")
        void dadoNoticiaValida_QuandoToString_EntaoRetornarStringIgual() {
            var resposta = "NoticiaBusiness{" +
                    "id=" + noticiaBusiness.getId() +
                    ", chapeu='" + noticiaBusiness.getChapeu() + '\'' +
                    ", titulo='" + noticiaBusiness.getTitulo() + '\'' +
                    ", linhaFina='" + noticiaBusiness.getLinhaFina() + '\'' +
                    ", lide='" + noticiaBusiness.getLide() + '\'' +
                    ", corpo='" + noticiaBusiness.getCorpo() + '\'' +
                    ", nomeAutor='" + noticiaBusiness.getNomeAutor() + '\'' +
                    ", fonte='" + noticiaBusiness.getFonte() + '\'' +
                    ", dataHoraCriacao=" + noticiaBusiness.getDataHoraCriacao() +
                    ", dataHoraAtualizacao=" + noticiaBusiness.getDataHoraAtualizacao() +
                    '}';
            Assertions.assertEquals(resposta, noticiaBusiness.toString());
        }

        @Test
        @Order(2)
        @DisplayName("equals")
        void dadoDuasNoticiasValidas_QuandoEquals_EntaoRetornarNotEquals() {
            var primeiraNoticia = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
            primeiraNoticia.setId(1L);

            var segundaNoticia = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
            segundaNoticia.setId(2L);

            Assertions.assertNotEquals(primeiraNoticia, segundaNoticia);
        }

        @Test
        @Order(3)
        @DisplayName("hashCode")
        void dadoDoisHashCodeValidos_QuandoHashCode_EntaoRetornarNotEquals() {
            var primeiraNoticia = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
            primeiraNoticia.setId(1L);

            var segundaNoticia = FabricaDeObjetosDeTeste.gerarNoticiaBusiness();
            segundaNoticia.setId(2L);

            var primeiroHash = primeiraNoticia.hashCode();
            var segundoHash = segundaNoticia.hashCode();

            Assertions.assertNotEquals(primeiroHash, segundoHash);
        }
    }
}

