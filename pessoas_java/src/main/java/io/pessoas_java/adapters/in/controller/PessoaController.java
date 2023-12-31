package io.pessoas_java.adapters.in.controller;

import io.pessoas_java.adapters.in.dto.filtro.PessoaDtoFiltro;
import io.pessoas_java.adapters.in.dto.request.PessoaCadastrarDtoIn;
import io.pessoas_java.adapters.in.dto.request.PessoaEditarDtoIn;
import io.pessoas_java.adapters.in.dto.response.PessoaCadastrarDtoOut;
import io.pessoas_java.adapters.in.dto.response.PessoaPesquisarDtoOut;
import io.pessoas_java.adapters.in.mapper.*;
import io.pessoas_java.adapters.in.utilitarios.ProdutorHateoas;
import io.pessoas_java.application.ports.in.*;
import io.pessoas_java.config.exceptions.RetornoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/pessoas")
@RequiredArgsConstructor
@Tag(name = "Pessoa", description = "Endpoints para gerenciar Pessoas.")
public class PessoaController {

    private final Logger logger = Logger.getLogger(PessoaController.class.getName());

    private final PessoaCadastrarInputPort pessoaCadastrarInputPort;

    private final PessoaConsultarPorChaveInputPort pessoaConsultarPorChaveInputPort;

    private final PessoaPesquisarInputPort pessoaPesquisarInputPort;

    private final PessoaEditarInputPort pessoaEditarInputPort;

    private final PessoaDeletarPorChaveInputPort pessoaDeletarPorChaveInputPort;

    private final PessoaCadastrarDtoInMapper pessoaCadastrarDtoInMapper;

    private final PessoaCadastrarDtoOutMapper pessoaCadastrarDtoOutMapper;

    private final PessoaPesquisarDtoOutMapper pessoaPesquisarDtoOutMapper;

    private final PessoaDtoFiltroMapper pessoaDtoFiltroMapper;

    private final PessoaEditarDtoInMapper pessoaEditarDtoInMapper;

    private final ProdutorHateoas produtorHateoas;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Criar uma Pessoa", description = "Criar uma Pessoa enviando JSon, XML ou YAML.",
        tags = {"Pessoa"},
            responses = {
                @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PessoaCadastrarDtoOut.class))
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Forbidden", responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                })
            }
    )
    public ResponseEntity<PessoaCadastrarDtoOut> cadastrar(@RequestBody @Valid PessoaCadastrarDtoIn dtoIn) {

        logger.info("Controller - recebida requisição para cadastrar uma pessoa.");

        var dtoOut = Optional.of(dtoIn)
            .map(this.pessoaCadastrarDtoInMapper::toPessoa)
            .map(this.pessoaCadastrarInputPort::cadastrar)
            .map(this.pessoaCadastrarDtoOutMapper::toPessoaCadastrarDtoOut)
            .map(this.produtorHateoas::links)
            .orElseThrow(NoSuchElementException::new);

        logger.info("Controller - concluído cadastro de uma pessoa.");

        return ResponseEntity
            .created(URI.create("/api/v1/pessoas/" + dtoOut.getKey()))
            .body(dtoOut);
    }

    @GetMapping(path = "/{chave}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Consultar uma Pessoa por chave", description = "Consultar uma Pessoa por chave",
        tags = {"Pessoa"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PessoaCadastrarDtoOut.class))
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Forbidden", responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Not Found", responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                })
            }
    )
    public ResponseEntity<PessoaCadastrarDtoOut> consultarPorChave(
        @Parameter(name = "chave", description = "UUID da Pessoa", example = "53c81a54-dad5-4b5f-a5e4-584c0b1fbdc3", required = true)
        @PathVariable(name = "chave") final UUID chave) {

        logger.info("Controller - recebida requisição para consultar pessoa por chave.");

        var dtoOut = Optional.of(chave)
            .map(this.pessoaConsultarPorChaveInputPort::consultarPorChave)
            .map(this.pessoaCadastrarDtoOutMapper::toPessoaCadastrarDtoOut)
            .map(this.produtorHateoas::links)
            .orElseThrow(NoSuchElementException::new);

        logger.info("Controller - concluído consultar pessoa por chave.");

        return ResponseEntity
            .ok()
            .body(dtoOut);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Pesquisar Pessoas", description = "Pesquisar Pessoas",
        tags = {"Pessoa"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = PessoaCadastrarDtoOut.class)))
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Forbidden", responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                })
            }
    )
    public ResponseEntity<Page<PessoaPesquisarDtoOut>> pesquisar(@Valid final PessoaDtoFiltro dtoFiltro,
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao) {

        logger.info("Controller - recebida requisição para pesquisar pessoas.");

        var paginaDtoOut = Optional.of(dtoFiltro)
            .map(this.pessoaDtoFiltroMapper::toPessoaFiltro)
            .map(filtro -> this.pessoaPesquisarInputPort.pesquisar(filtro, paginacao))
            .map(pagina -> pagina.map(this.pessoaPesquisarDtoOutMapper::toPessoaPesquisarDtoOut))
            .map(pagina -> pagina.map(this.produtorHateoas::links))
            .orElseThrow(NoSuchElementException::new);

        logger.info("Controller - concluído pesquisar pessoas.");

        return ResponseEntity
            .ok()
            .body(paginaDtoOut);
    }

    @PutMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Editar uma Pessoa por chave", description = "Editar uma Pessoa por chave enviando JSon, XML ou YAML.",
        tags = {"Pessoa"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PessoaCadastrarDtoOut.class))
                }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Forbidden", responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Not Found", responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                })
            }
    )
    public ResponseEntity<PessoaCadastrarDtoOut> editar(@RequestBody @Valid PessoaEditarDtoIn editarDtoIn) {

        logger.info("Controller - recebida requisição para editar uma pessoa.");

        var dtoOut = Optional.of(editarDtoIn)
            .map(this.pessoaEditarDtoInMapper::toPessoa)
            .map(this.pessoaEditarInputPort::editar)
            .map(this.pessoaCadastrarDtoOutMapper::toPessoaCadastrarDtoOut)
            .map(this.produtorHateoas::links)
            .orElseThrow(NoSuchElementException::new);

        logger.info("Controller - concluído editar pessoa.");

        return ResponseEntity
            .ok()
            .body(dtoOut);
    }

    @DeleteMapping(path = "/{chave}")
    @Operation(summary = "Apagar uma Pessoa por chave", description = "Apagar uma Pessoa por chave",
        tags = {"Pessoa"},
            responses = {
                @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Forbidden", responseCode = "403", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Not Found", responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                }),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                    @Content(schema = @Schema(implementation = RetornoException.class))
                })
            }
    )
    public ResponseEntity<Void> deletarPorChave(
        @PathVariable(name = "chave") @Parameter(name = "chave", description = "UUID da Pessoa",
            example = "53c81a54-dad5-4b5f-a5e4-584c0b1fbdc3", required = true) final UUID chave) {

        logger.info("Controller - recebida requisição para deletar uma pessoa por chave.");

        this.pessoaDeletarPorChaveInputPort.deletar(chave);

        logger.info("Controller - concluído deletar uma pessoa por chave.");

        return ResponseEntity
            .noContent()
            .build();
    }
}

