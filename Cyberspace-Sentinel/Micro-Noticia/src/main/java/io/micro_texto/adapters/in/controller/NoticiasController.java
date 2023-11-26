package io.micro_texto.adapters.in.controller;

import io.micro_texto.adapters.in.dto.request.NoticiaCriarDtoIn;
import io.micro_texto.adapters.in.dto.response.NoticiaCriarDtoOut;
import io.micro_texto.adapters.in.mapper.NoticiaDtoMapper;
import io.micro_texto.application.ports.input.NoticiaCriarInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/noticias")
@RequiredArgsConstructor
public class NoticiasController {

    private final Logger log = Logger.getLogger(NoticiasController.class.getName());

    private final NoticiaCriarInputPort noticiaCriarInputPort;

    private final NoticiaDtoMapper noticiaDtoMapper;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<NoticiaCriarDtoOut> criar(@RequestBody NoticiaCriarDtoIn dtoIn) {
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
}

