package io.pessoas_java.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum NivelEducacionalEnum {

    ANALFABETO("Analfabeto"),
    ENSINO_FUNDAMENTAL_COMPLETO("Ensino Fundamental Completo"),
    ENSINO_MEDIO_COMPLETO("Ensino Medio Completo"),
    ENSINO_SUPERIOR_COMPLETO("Ensino Superior Completo"),
    POS_GRADUACAO_LATO_SENSU_COMPLETA("Pos-graduação Lato Sensu Completa"),
    MESTRADO_COMPLETO("Mestrado Completo"),
    DOUTORADO_COMPLETO("Doutorado Completo");

    private String nivel;

    NivelEducacionalEnum(String nivel) {
        this.nivel = nivel;
    }
}

