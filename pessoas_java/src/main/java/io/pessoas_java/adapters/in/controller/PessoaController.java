package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.adapters.in.dto.request.PessoaDtoFiltro;
import io.pessoas_java.adapters.in.dto.request.PessoaDtoIn;
import io.pessoas_java.adapters.in.dto.response.PessoaDtoOut;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/pessoas")
public class PessoaController {

    @PostMapping
    public ResponseEntity<PessoaDtoOut> cadastrar(@RequestBody @Valid PessoaDtoIn dtoIn) {


        return ResponseEntity
                .created(URI.create("/api/v1/pessoas/" + 1))
                .body(null);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDtoOut>> pesquisar(@Valid final PessoaDtoFiltro dtoFiltro,
                                                        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao) {


        return ResponseEntity
                .ok()
                .body(null);
    }

    @PutMapping(path = "/{chave}")
    public ResponseEntity<PessoaDtoOut> editar(@PathVariable(name = "chave") final UUID chave,
                                               @RequestBody @Valid PessoaDtoIn dtoIn) {


        return ResponseEntity
                .ok()
                .body(null);
    }

    @DeleteMapping(path = "/{chave}")
    public ResponseEntity<Void> deletar(@PathVariable(name = "chave") final UUID chave) {


        return ResponseEntity
                .noContent()
                .build();
    }
}

