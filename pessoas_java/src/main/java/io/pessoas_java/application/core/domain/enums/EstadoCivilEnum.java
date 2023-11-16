package io.pessoas_java.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoCivilEnum {

    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    DIVORCIADO("Divorciado"),
    VIÚVO("Viúvo"),
    SEPARADO("Separado"),
    UNIÃO_ESTÁVEL("União Estável"),
    OUTRO("Outro");

    private String tipo;

    EstadoCivilEnum(String tipo) {
        this.tipo = tipo;
    }
}

