package io.micronoticias.adapter.in.controller;

import io.micronoticias.adapter.in.dto.filtro.NoticiaFiltroDtoIn;
import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.adapter.in.dto.response.NoticiaPesquisarDtoOut;
import io.micronoticias.adapter.in.mapper.NoticiaMapperDtoIn;
import io.micronoticias.adapter.in.mapper.NoticiaMapperDtoOut;
import io.micronoticias.application.port.in.NoticiaCriarInputPort;
import io.micronoticias.application.port.in.NoticiaPesquisarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private static final Logger log = LoggerFactory.getLogger(NoticiaController.class);

    private final NoticiaCriarInputPort criarInputPort;

    private final NoticiaPesquisarInputPort pesquisarInputPort;

    private final NoticiaMapperDtoIn mapperIn;

    private final NoticiaMapperDtoOut mapperOut;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NoticiaCriarDtoOut> criar(@RequestBody @Valid NoticiaCriarDtoIn dtoIn) {

        log.info("Recebida requisição para criar Notícia: {}", dtoIn.titulo());

        var resposta = Optional.ofNullable(dtoIn)
                .map(this.mapperIn::toNoticiaBusiness)
                .map(this.criarInputPort::criar)
                .map(this.mapperOut::toNoticiaCriarDtoOut)
                .orElseThrow();

        log.info("Notícia criada com sucesso: {}", dtoIn.titulo());

        return ResponseEntity
                .created(URI.create("/api/v1/noticias/" + resposta.id()))
                .body(resposta);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<NoticiaPesquisarDtoOut>> pesquisar(
        @Valid final NoticiaFiltroDtoIn noticiaFiltroDtoIn,
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

        log.info("Requisição recebida para pesquisar Notícias.");

        var resposta = Optional.ofNullable(noticiaFiltroDtoIn)
                .map(this.mapperIn::toNoticiaFiltro)
                .map(filtro -> this.pesquisarInputPort.pesquisar(filtro, paginacao))
                .map(pagina -> pagina.map(this.mapperOut::toNoticiaPesquisarDtoOut))
                .orElseThrow();

        log.info("Notícia pesquisada com sucesso!");

        return ResponseEntity
                .ok()
                .body(resposta);
    }
}

