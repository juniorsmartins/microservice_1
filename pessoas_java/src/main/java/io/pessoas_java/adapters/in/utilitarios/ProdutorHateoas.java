package io.pessoas_java.adapters.in.utilitarios;

import io.pessoas_java.adapters.in.controller.PessoaController;
import io.pessoas_java.adapters.in.dto.response.PessoaCadastrarDtoOut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public final class ProdutorHateoas {

    public PessoaCadastrarDtoOut links(PessoaCadastrarDtoOut dtoOut) {

        dtoOut.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(PessoaController.class).consultarPorChave(dtoOut.getKey())).withSelfRel());

        dtoOut.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(PessoaController.class).deletarPorChave(dtoOut.getKey())).withRel("delete"));

        return dtoOut;
    }
}

