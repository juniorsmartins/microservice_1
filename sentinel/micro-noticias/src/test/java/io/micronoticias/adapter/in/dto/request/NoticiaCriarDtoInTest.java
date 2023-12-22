package io.micronoticias.adapter.in.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Classe NotíciaCriarDtoIn")
class NoticiaCriarDtoInTest {

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
}

