package io.pessoas_java.config.exceptions;

import io.pessoas_java.config.exceptions.http_400.RequiredObjectIsNullException;
import io.pessoas_java.config.exceptions.http_400.RequisicaoMalFormuladaException;
import io.pessoas_java.config.exceptions.http_404.RecursoNaoEncontradoException;
import io.pessoas_java.config.exceptions.http_409.RegraDeNegocioVioladaException;
import io.pessoas_java.config.exceptions.http_500.ErroInternoQualquerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratamentoExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RetornoException> handlerAllExceptions(Exception ex, WebRequest webRequest) {

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var tipoDeErroEnum = TipoDeErroEnum.PROBLEMA_INTERNO_SERVIDOR;
        var detalhe = ex.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErroInternoQualquerException.class)
    public final ResponseEntity<RetornoException> tratarErroInterno(ErroInternoQualquerException err, WebRequest webRequest) {

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var tipoDeErroEnum = TipoDeErroEnum.PROBLEMA_INTERNO_SERVIDOR;
        var detalhe = err.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequisicaoMalFormuladaException.class)
    public final ResponseEntity<RetornoException> tratarRequisicaoMalFormulada(RequisicaoMalFormuladaException ex, WebRequest webRequest) {

        var httpStatus = HttpStatus.BAD_REQUEST;
        var tipoDeErroEnum = TipoDeErroEnum.REQUISICAO_MAL_FORMULADA;
        var detalhe = ex.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<RetornoException> tratarRequiredObjectIsNull(RequiredObjectIsNullException req, WebRequest webRequest) {

        var httpStatus = HttpStatus.BAD_REQUEST;
        var tipoDeErroEnum = TipoDeErroEnum.REQUISICAO_MAL_FORMULADA;
        var detalhe = req.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<RetornoException> tratarNoSuchElement(NoSuchElementException ex, WebRequest webRequest) {

        var httpStatus = HttpStatus.BAD_REQUEST;
        var tipoDeErroEnum = TipoDeErroEnum.REQUISICAO_MAL_FORMULADA;
        var detalhe = ex.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegraDeNegocioVioladaException.class)
    public final ResponseEntity<RetornoException> tratarRegraViolada(RegraDeNegocioVioladaException ex, WebRequest webRequest) {

        var httpStatus = HttpStatus.CONFLICT;
        var tipoDeErroEnum = TipoDeErroEnum.REGRA_NEGOCIO_VIOLADA;
        var detalhe = ex.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public final ResponseEntity<RetornoException> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex, WebRequest webRequest) {

        var httpStatus = HttpStatus.NOT_FOUND;
        var tipoDeErroEnum = TipoDeErroEnum.RECURSO_NAO_ENCONTRADO;
        var detalhe = ex.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.NOT_FOUND);
    }

    // Método para construção da mensagem de retorno
    private RetornoException.RetornoExceptionBuilder criarMensagemParaRetornarException(
            HttpStatusCode httpStatusCode, TipoDeErroEnum tipoDeErroEnum, String detalhe) {

        return RetornoException.builder()
                .tipo(tipoDeErroEnum.getCaminho())
                .titulo(tipoDeErroEnum.getTitulo())
                .status(httpStatusCode.value())
                .detalhe(detalhe)
//      .instancia()
                .dataHoraErro(Instant.now());
    }
}

