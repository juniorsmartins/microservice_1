package io.micronoticias.adapter.in.controller;

import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;
import io.micronoticias.adapter.in.dto.response.NoticiaCriarDtoOut;
import io.micronoticias.adapter.mapper.NoticiaMapper;
import io.micronoticias.application.port.in.NoticiaCriarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    private final NoticiaMapper mapper;

    @PostMapping
    public ResponseEntity<NoticiaCriarDtoOut> criar(@RequestBody @Valid NoticiaCriarDtoIn dtoIn) {

        var resposta = Optional.of(dtoIn)
                .map(this.mapper::toNoticiaBusiness)
                .map(this.criarInputPort::criar)
                .map(this.mapper::fromNoticiaBusiness)
                .orElseThrow(NoSuchElementException::new);

        return ResponseEntity
                .created(URI.create("/api/v1/noticias/" + resposta.id()))
                .body(resposta);
    }
}
