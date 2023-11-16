package io.pessoas_java.adapters.in.utilitarios;

import io.pessoas_java.adapters.in.dto.response.PessoaCadastrarDtoOut;
import io.pessoas_java.adapters.in.dto.response.PessoaPesquisarDtoOut;

public interface ProdutorHateoas {

    PessoaCadastrarDtoOut links(PessoaCadastrarDtoOut dtoOut);

    PessoaPesquisarDtoOut links(PessoaPesquisarDtoOut dtoOut);
}

