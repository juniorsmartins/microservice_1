package io.pessoas_java.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoCivilEnum {

    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viuvo"),
    SEPARADO("Separado"),
    UNIAO_ESTAVEL("Uniao Estavel"),
    OUTRO("Outro");

    private String tipo;

    EstadoCivilEnum(String tipo) {
        this.tipo = tipo;
    }
}

