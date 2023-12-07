package io.micro_noticia.adapters.in.controller;

import io.micro_noticia.adapters.in.dto.request.NoticiaCriarDtoIn;
import io.micro_noticia.adapters.in.dto.response.NoticiaCriarDtoOut;
import io.micro_noticia.adapters.in.mapper.NoticiaDtoMapper;
import io.micro_noticia.application.ports.input.NoticiaCriarInputPort;
import io.micro_noticia.application.ports.input.NoticiaRevisionsInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/noticias")
@RequiredArgsConstructor
public class NoticiasController {

    private final Logger log = Logger.getLogger(NoticiasController.class.getName());

    private final NoticiaCriarInputPort noticiaCriarInputPort;

    private final NoticiaRevisionsInputPort noticiaRevisionsInputPort;

    private final NoticiaDtoMapper noticiaDtoMapper;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<NoticiaCriarDtoOut> criar(@RequestBody @Valid NoticiaCriarDtoIn dtoIn) {
        log.info("Controller - recebida requisição para Criar Notícia.");

        var resposta = Optional.of(dtoIn)
            .map(this.noticiaDtoMapper::toNoticiaBusiness)
            .map(this.noticiaCriarInputPort::criar)
            .map(this.noticiaDtoMapper::toNoticiaCriarDtoOut)
            .orElseThrow();

        log.info("Controller - concluída requisição para Criar Notícia.");

        return ResponseEntity
            .created(URI.create("/api/v1/noticias/" + resposta.id()))
            .body(resposta);
    }

    @GetMapping(path = "/revisions/{id}")
    public ResponseEntity<List<String>> revisions(@PathVariable(name = "id") final Long id) {
        log.info("Controller - recebida requisição para buscar Auditoria por Id de Notícia.");

        var resposta = this.noticiaRevisionsInputPort.listarRevisoesPorId(id);

        log.info("Controller - concluída requisição para buscar Auditoria por Id de Notícia.");

        return ResponseEntity
            .ok()
            .body(resposta);
    }
}

