package io.micro_texto.config.exception;

import io.micro_texto.config.exception.http_400.RequisicaoMalFormuladaException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource mensagemInternacionalizada;

    public GlobalExceptionHandler(MessageSource mensagemInternacionalizada) {
        this.mensagemInternacionalizada = mensagemInternacionalizada;
    }

    @ExceptionHandler(value = RequisicaoMalFormuladaException.class)
    public ResponseEntity<Object> tratarRequisicaoMalFormulada(RequisicaoMalFormuladaException ex,
                                                               WebRequest webRequest) {

        var tipoErroEnum = TipoDeErroEnum.REQUISICAO_MAL_FORMULADA;
        var httpStatus = HttpStatus.BAD_REQUEST;
        var detail = ex.getMessage();

        var mensagemDeErro = this.criarMensagemDeRetorno(tipoErroEnum, httpStatus, detail)
            .build();

        return this.handleExceptionInternal(ex, mensagemDeErro, new HttpHeaders(), httpStatus, webRequest);
    }

    // Método para construção da mensagem de retorno
    private ApiError.ApiErrorBuilder criarMensagemDeRetorno(TipoDeErroEnum tipoEnum, HttpStatus httpStatus, String detail) {

        return ApiError.builder()
            .type(tipoEnum.getCaminho())
            .title(tipoEnum.getTitulo())
            .status(httpStatus.value())
            .statusText(httpStatus.name())
            .detail(detail)
    //                .instance()
            .dataHoraErro(Instant.now());
    }

    // Sobrescrição de um método comum de ResponseEntityExceptionHandler. Esse método é chamado por vários
    // outros métodos de tratamento de exceção. Então, ao personalizá-lo, nós interferimos nas respostas de
    // retorno de erro de vários métodos do sistema (ResponseEntityExceptionHandler).
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest webRequest) {

        if (body == null) {
            body = ApiError.builder()
                .status(statusCode.value())
                .statusText(statusCode.toString())
                .detail(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                .dataHoraErro(Instant.now())
                .build();

            return super.handleExceptionInternal(ex, body, headers, statusCode, webRequest);

        } else if (body instanceof String) {
            body = ApiError.builder()
                .status(statusCode.value())
                .statusText(statusCode.toString())
                .detail(body.toString())
                .dataHoraErro(Instant.now())
                .build();

        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, webRequest);
    }


//     BadRequestException
//
//     NotFoundException
//
//     Exception
//
//     NullPointerException
//
//     JsonMappingException
//
//     RuntimeException
//
//     BindException
//
//     EntityControlException

    // ConstraintViolationException

    // IllegalArgumentException

    // AccessDeniedException

    //
}

