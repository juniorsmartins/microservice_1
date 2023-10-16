package io.pessoas_java.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class TratamentoExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RetornoException> handleAllExceptions(Exception ex, WebRequest webRequest) {

        var httpStatus = HttpStatus.BAD_REQUEST;
        var tipoDeErroEnum = TipoDeErroEnum.REQUISICAO_MAL_FORMULADA;
        var detalhe = ex.getMessage();

        var retornoException = this.criarMensagemParaRetornarException(httpStatus, tipoDeErroEnum, detalhe).build();

        return new ResponseEntity<>(retornoException, HttpStatus.INTERNAL_SERVER_ERROR);
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

