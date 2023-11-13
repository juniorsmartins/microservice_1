package io.pessoas_java.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum SexoEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String tipo;

    SexoEnum(String tipo) {
        this.tipo = tipo;
    }
}

