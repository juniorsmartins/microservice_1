package io.pessoas_java.adapters.in.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Builder
public record PessoaCadastrarDtoIn(

        @NotBlank
        @Length(max = 30)
        String nome,

        @NotBlank
        @Length(max = 50)
        String sobrenome,

        @CPF
        @NotBlank
        @Length(max = 11)
        String cpf,

        @NotBlank
        @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Formato de data inválido. Use dd/MM/yyyy.")
        String dataNascimento,

        @NotBlank
        String sexo,

        String genero,

        @NotBlank
        String nivelEducacional,

        @NotBlank
        String nacionalidade,

        @NotBlank
        String estadoCivil,

        @Valid
        Set<TelefoneCadastrarDtoIn> telefones,

        @NotEmpty
        @Valid
        Set<EmailCadastrarDtoIn> emails,

        @NotNull
        @Valid
        EnderecoCadastrarDtoIn endereco
) { }

