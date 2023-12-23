package io.micronoticias.adapter.in.dto.request;

import com.github.javafaker.Faker;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Classe NotíciaCriarDtoIn")
class NoticiaCriarDtoInTest {

    public static Faker faker = new Faker();

    @Autowired
    private Validator validator;

    @Nested
    @DisplayName("Chapéu")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Chapeu {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoChapeuNulo_QuandoValidar_EntaoLancarException() {
            var dtoIn = new NoticiaCriarDtoIn(null, "Titulo", "Linha Fina", "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoChapeuValorVazio_QuandoValidar_EntaoLancarException(String chapeu) {
            var dtoIn = new NoticiaCriarDtoIn(chapeu, "Titulo", "Linha Fina", "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoChapeuComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var dtoIn = new NoticiaCriarDtoIn("21_caracteres--------", "Titulo", "Linha Fina", "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Titulo")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Titulo {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoTituloNulo_QuandoValidar_EntaoLancarException() {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", null, "Linha Fina", "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoTituloValorVazio_QuandoValidar_EntaoLancarException(String titulo) {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", titulo, "Linha Fina", "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoTituloComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var titulo = faker.lorem().characters(101, 150);
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", titulo, "Linha Fina", "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Linha Fina")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class LinhaFina {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoLinhaFinaNulo_QuandoValidar_EntaoLancarException() {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", null, "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoLinhaFinaValorVazio_QuandoValidar_EntaoLancarException(String linhaFina) {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", linhaFina, "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoLinhaFinaComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var linhaFina = faker.lorem().characters(151, 200);
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", linhaFina, "Lide", "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Lide")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Lide {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoLideNulo_QuandoValidar_EntaoLancarException() {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", null, "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoLideValorVazio_QuandoValidar_EntaoLancarException(String lide) {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", lide, "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoLideComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var lide = faker.lorem().characters(501, 550);
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", lide, "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Corpo")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Corpo {

        @Test
        @Order(1)
        @DisplayName("nulo")
        void dadoCorpoNulo_QuandoValidar_EntaoLancarException() {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", "Lide", null, "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @Order(2)
        @DisplayName("vazio ou em branco")
        void dadoCorpoValorVazio_QuandoValidar_EntaoLancarException(String lide) {
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", lide, "Corpo", "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @Order(3)
        @DisplayName("com tamanho inválido")
        void dadoCorpoComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var corpo = faker.lorem().characters(5001, 5050);
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", "Lide", corpo, "Nome Autor", "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Nome Autor")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class NomeAutor {

        @Test
        @Order(1)
        @DisplayName("com tamanho inválido")
        void dadoNomeAutorComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var nomeAutor = faker.lorem().characters(51, 60);
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", "Lide", "Corpo", nomeAutor, "Fonte");
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Fonte")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Fonte {

        @Test
        @Order(1)
        @DisplayName("com tamanho inválido")
        void dadoFonteComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var fonte = faker.lorem().characters(251, 260);
            var dtoIn = new NoticiaCriarDtoIn("Chapéu", "Título", "Linha Fina", "Lide", "Corpo", "Nome Autor", fonte);
            Set<ConstraintViolation<NoticiaCriarDtoIn>> violations = validator.validate(dtoIn);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }
}

