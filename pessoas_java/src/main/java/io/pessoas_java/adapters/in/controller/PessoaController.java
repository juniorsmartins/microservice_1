package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.adapters.in.dto.request.PessoaDtoFiltro;
import io.pessoas_java.adapters.in.dto.request.PessoaDtoIn;
import io.pessoas_java.adapters.in.dto.request.PessoaEditarDtoIn;
import io.pessoas_java.adapters.in.dto.response.PessoaDtoOut;
import io.pessoas_java.adapters.in.mapper.PessoaDtoFiltroMapper;
import io.pessoas_java.adapters.in.mapper.PessoaDtoInMapper;
import io.pessoas_java.adapters.in.mapper.PessoaDtoOutMapper;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;
import io.pessoas_java.application.ports.in.PessoaPesquisarInputPort;
import io.pessoas_java.config.exceptions.http_500.ErroInternoQualquerException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaCadastrarInputPort pessoaCadastrarInputPort;

    @Autowired
    private PessoaPesquisarInputPort pessoaPesquisarInputPort;

    @Autowired
    private PessoaDtoInMapper pessoaDtoInMapper;

    @Autowired
    private PessoaDtoOutMapper pessoaDtoOutMapper;

    @Autowired
    private PessoaDtoFiltroMapper pessoaDtoFiltroMapper;

    @PostMapping
    public ResponseEntity<PessoaDtoOut> cadastrar(@RequestBody @Valid PessoaDtoIn dtoIn) {

        var dtoOut = Optional.of(dtoIn)
            .map(this.pessoaDtoInMapper::toPessoa)
            .map(this.pessoaCadastrarInputPort::cadastrar)
            .map(this.pessoaDtoOutMapper::toPessoaDtoOut)
            .orElseThrow(ErroInternoQualquerException::new);

        return ResponseEntity
            .created(URI.create("/api/v1/pessoas/" + dtoOut.chave()))
            .body(dtoOut);
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDtoOut>> pesquisar(@Valid final PessoaDtoFiltro dtoFiltro,
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10)
        final Pageable paginacao) {

        var paginaDtoOut = Optional.of(dtoFiltro)
            .map(this.pessoaDtoFiltroMapper::toPessoaFiltro)
            .map(filtro -> this.pessoaPesquisarInputPort.pesquisar(filtro, paginacao))
            .map(pagina -> pagina.map(this.pessoaDtoOutMapper::toPessoaDtoOut))
            .orElseThrow(ErroInternoQualquerException::new);

        return ResponseEntity
            .ok()
            .body(paginaDtoOut);
    }

    @PutMapping(path = "/{chave}")
    public ResponseEntity<PessoaDtoOut> editar(@PathVariable(name = "chave") final UUID chave,
                                               @RequestBody @Valid PessoaEditarDtoIn dtoIn) {


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

