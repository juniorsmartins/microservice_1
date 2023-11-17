package io.pessoas_java.adapters.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.pessoas_java.application.core.domain.enums.EstadoCivilEnum;
import io.pessoas_java.application.core.domain.enums.NivelEducacionalEnum;
import io.pessoas_java.application.core.domain.enums.SexoEnum;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"cpf"}, callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"chave", "nome", "sobrenome", "cpf", "dataNascimento", "sexo", "genero", "nivelEducacional", "nacionalidade", "estadoCivil", "telefones", "emails", "endereco"})
public class PessoaCadastrarDtoOut extends RepresentationModel<PessoaCadastrarDtoOut> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("chave")
    private UUID key;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String dataNascimento;

    private SexoEnum sexo;

    private String genero;

    private NivelEducacionalEnum nivelEducacional;

    private String nacionalidade;

    private EstadoCivilEnum estadoCivil;

    private Set<TelefoneCadastrarDtoOut> telefones;

    private Set<EmailCadastrarDtoOut> emails;

    private EnderecoCadastrarDtoOut endereco;
}

