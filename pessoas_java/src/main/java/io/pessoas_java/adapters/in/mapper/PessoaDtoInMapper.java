package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.request.PessoaDtoIn;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.enums.EstadoCivilEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PessoaDtoInMapper {

    @Mapping(target = "estadoCivil", source = "estadoCivil", qualifiedByName = "estadoCivilStringToEnum")
    Pessoa toPessoa(PessoaDtoIn pessoaDtoIn);

    @Named("estadoCivilStringToEnum")
    default EstadoCivilEnum estadoCivilStringToEnum(String estadoCivil) {
        for (EstadoCivilEnum enumValue : EstadoCivilEnum.values()) {
            if (enumValue.getTipo().equalsIgnoreCase(estadoCivil)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Estado civil desconhecido: " + estadoCivil);
    }
}

