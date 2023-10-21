package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.adapters.in.dto.request.PessoaDtoFiltro;
import io.pessoas_java.adapters.in.dto.request.PessoaDtoIn;
import io.pessoas_java.adapters.in.dto.request.PessoaEditarDtoIn;
import io.pessoas_java.adapters.in.dto.response.PessoaDtoOut;
import io.pessoas_java.adapters.in.mapper.PessoaDtoFiltroMapper;
import io.pessoas_java.adapters.in.mapper.PessoaDtoInMapper;
import io.pessoas_java.adapters.in.mapper.PessoaDtoOutMapper;
import io.pessoas_java.adapters.in.utilitarios.ProdutorHateoas;
import io.pessoas_java.application.ports.in.PessoaCadastrarInputPort;
import io.pessoas_java.application.ports.in.PessoaConsultarPorChaveInputPort;
import io.pessoas_java.application.ports.in.PessoaPesquisarInputPort;
import io.pessoas_java.config.exceptions.http_500.ErroInternoQualquerException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/pessoas")
public class PessoaController {

    private final Logger logger = Logger.getLogger(PessoaController.class.getName());

    @Autowired
    private PessoaCadastrarInputPort pessoaCadastrarInputPort;

    @Autowired
    private PessoaPesquisarInputPort pessoaPesquisarInputPort;

    @Autowired
    private PessoaConsultarPorChaveInputPort pessoaConsultarPorChaveInputPort;

    @Autowired
    private PessoaDtoInMapper pessoaDtoInMapper;

    @Autowired
    private PessoaDtoOutMapper pessoaDtoOutMapper;

    @Autowired
    private PessoaDtoFiltroMapper pessoaDtoFiltroMapper;

    @Autowired
    private ProdutorHateoas produtorHateoas;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public ResponseEntity<PessoaDtoOut> cadastrar(@RequestBody @Valid PessoaDtoIn dtoIn) {

        logger.info("Controller - recebida requisição para cadastrar uma pessoa.");

        var dtoOut = Optional.of(dtoIn)
            .map(this.pessoaDtoInMapper::toPessoa)
            .map(this.pessoaCadastrarInputPort::cadastrar)
            .map(this.pessoaDtoOutMapper::toPessoaDtoOut)
            .map(this.produtorHateoas::links)
            .orElseThrow(ErroInternoQualquerException::new);

        logger.info("Controller - concluído cadastro de uma pessoa.");

        return ResponseEntity
            .created(URI.create("/api/v1/pessoas/" + dtoOut.getKey()))
            .body(dtoOut);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public ResponseEntity<Page<PessoaDtoOut>> pesquisar(@Valid final PessoaDtoFiltro dtoFiltro,
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10)
        final Pageable paginacao) {

        logger.info("Controller - recebida requisição para pesquisar pessoas.");

        var paginaDtoOut = Optional.of(dtoFiltro)
            .map(this.pessoaDtoFiltroMapper::toPessoaFiltro)
            .map(filtro -> this.pessoaPesquisarInputPort.pesquisar(filtro, paginacao))
            .map(pagina -> pagina.map(this.pessoaDtoOutMapper::toPessoaDtoOut))
            .map(pagina -> pagina.map(this.produtorHateoas::links))
            .orElseThrow(ErroInternoQualquerException::new);

        logger.info("Controller - concluído pesquisar pessoas.");

        return ResponseEntity
            .ok()
            .body(paginaDtoOut);
    }

    @GetMapping(path = "/{chave}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public ResponseEntity<PessoaDtoOut> consultarPorChave(@PathVariable(name = "chave") final UUID chave) {

        logger.info("Controller - recebida requisição para consultar pessoa por chave.");

        var dtoOut = Optional.of(chave)
            .map(this.pessoaConsultarPorChaveInputPort::consultarPorChave)
            .map(this.pessoaDtoOutMapper::toPessoaDtoOut)
            .map(this.produtorHateoas::links)
            .orElseThrow(ErroInternoQualquerException::new);

        logger.info("Controller - concluído consultar pessoa por chave.");

        return ResponseEntity
            .ok()
            .body(dtoOut);
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

