package io.pessoas_java.adapters.in.utilitarios;

import io.pessoas_java.adapters.in.controller.PessoaController;
import io.pessoas_java.adapters.in.dto.request.PessoaDtoFiltro;
import io.pessoas_java.adapters.in.dto.response.PessoaDtoOut;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class ProdutorHateoas {

    public static PessoaDtoOut post(PessoaDtoOut dtoOut) {

        var filtro = new PessoaDtoFiltro(dtoOut.getKey(), null, null, null, null, null, null, null, null);
        Pageable paginacao = PageRequest.of(0, 10, Sort.by("nome").ascending());

        dtoOut.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(PessoaController.class).pesquisar(filtro, paginacao)).withSelfRel());
        return dtoOut;
    }
}

