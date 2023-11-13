package io.pessoas_java.adapters.in.mapper;

import io.pessoas_java.adapters.in.dto.response.PessoaCadastrarDtoOut;
import io.pessoas_java.application.core.domain.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PessoaDtoOutMapper {

    @Mapping(source = "chave", target = "key")
    @Mapping(target = "dataNascimento", source = "dataNascimento", qualifiedByName = "dataNascimentoLocalDateToString")
    PessoaCadastrarDtoOut toPessoaDtoOut(Pessoa pessoa);

    @Named("dataNascimentoLocalDateToString")
    default String dataNascimentoLocalDateToString(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatter);
    }
}

