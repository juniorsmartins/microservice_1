package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.request.PessoaEditarDtoIn;
import io.pessoas_java.application.core.domain.Pessoa;
import io.pessoas_java.application.core.domain.enums.EstadoCivilEnum;
import io.pessoas_java.application.core.domain.enums.NivelEducacionalEnum;
import io.pessoas_java.application.core.domain.enums.SexoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PessoaEditarDtoInMapper {

    @Mapping(target = "dataNascimento", source = "dataNascimento", qualifiedByName = "dataNascimentoStringToLocalDate")
    @Mapping(target = "estadoCivil", source = "estadoCivil", qualifiedByName = "estadoCivilStringToEnum")
    @Mapping(target = "sexo", source = "sexo", qualifiedByName = "sexoStringToEnum")
    @Mapping(target = "nivelEducacional", source = "nivelEducacional", qualifiedByName = "nivelEducacionalStringToEnum")
    Pessoa toPessoa(PessoaEditarDtoIn editarDtoIn);

    @Named("dataNascimentoStringToLocalDate")
    default LocalDate dataNascimentoStringToLocalDate(String dataNascimento) {
        var formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataNascimento, formatoEntrada);
    }

    @Named("sexoStringToEnum")
    default SexoEnum sexoStringToEnum(String sexo) {
        for (SexoEnum enumValue : SexoEnum.values()) {
            if (enumValue.getTipo().equalsIgnoreCase(sexo)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Sexo desconhecido: " + sexo);
    }

    @Named("nivelEducacionalStringToEnum")
    default NivelEducacionalEnum nivelEducacionalStringToEnum(String nivelEducacional) {
        for (NivelEducacionalEnum enumValue : NivelEducacionalEnum.values()) {
            if (enumValue.getNivel().equalsIgnoreCase(nivelEducacional)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Nível Educacional desconhecido: " + nivelEducacional);
    }

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

