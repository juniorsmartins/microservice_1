package io.micronoticias.adapter.in.controller;

import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.adapter.in.mapper.NoticiaMapperDtoIn;
import io.micronoticias.adapter.in.mapper.NoticiaMapperDtoOut;
import io.micronoticias.application.port.in.NoticiaCriarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/noticias")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaCriarInputPort criarInputPort;

    private final NoticiaMapperDtoIn mapperIn;

    private final NoticiaMapperDtoOut mapperOut;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NoticiaCriarDtoOut> criar(@RequestBody @Valid NoticiaCriarDtoIn dtoIn) {

        var resposta = Optional.ofNullable(dtoIn)
                .map(this.mapperIn::toNoticiaBusiness)
                .map(this.criarInputPort::criar)
                .map(this.mapperOut::toNoticiaCriarDtoOut)
                .orElseThrow();

        return ResponseEntity
                .created(URI.create("/api/v1/noticias/" + resposta.id()))
                .body(resposta);
    }
}

