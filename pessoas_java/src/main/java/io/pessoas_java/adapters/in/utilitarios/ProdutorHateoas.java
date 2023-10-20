package io.pessoas_java.adapters.in.utilitarios;

import io.pessoas_java.adapters.in.controller.PessoaController;
import io.pessoas_java.adapters.in.dto.response.PessoaDtoOut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class ProdutorHateoas {

    public static PessoaDtoOut post(PessoaDtoOut dtoOut) {

        dtoOut.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(PessoaController.class).consultarPorChave(dtoOut.getKey())).withSelfRel());
        return dtoOut;
    }
}

