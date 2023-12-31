package io.micro_noticia.config.exception;

import io.micro_noticia.config.exception.http_400.DadoInvalidoException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource mensagemInternacionalizada;

    public GlobalExceptionHandler(MessageSource mensagemInternacionalizada) {
        this.mensagemInternacionalizada = mensagemInternacionalizada;
    }

    @ExceptionHandler(value = DadoInvalidoException.class)
    public ResponseEntity<Object> tratarDadoInvalido(DadoInvalidoException ex, WebRequest webRequest) {

        var tipoErroEnum = TipoDeErroEnum.DADOS_INVALIDOS;
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

//     NotFoundException

//     Exception

//     NullPointerException

//     JsonMappingException

//     RuntimeException

//     BindException

//     EntityControlException

    // ConstraintViolationException

    // IllegalArgumentException

    // AccessDeniedException

    // Sobrescrição de um método comum de ResponseEntityExceptionHandler. Captura exceção quando o tipo de mediaType
    // for incompatível.
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatusCode status,
                                                                      WebRequest request) {
        return ResponseEntity
            .status(status)
            .headers(headers)
            .build();
    }


    // Aqui o tratamendo de anotações de BeanValidation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException argumentNotValid,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        return this.construirResponseComMensagemDeErros(argumentNotValid, argumentNotValid.getBindingResult(),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> construirResponseComMensagemDeErros(Exception exception, BindingResult bindingResult,
                                                                       HttpHeaders headers, HttpStatusCode status,
                                                                       WebRequest request) {
        var tipoDeErroEnum = TipoDeErroEnum.REQUISICAO_MAL_FORMULADA;
        var httpStatus = HttpStatus.BAD_REQUEST;
        var detalhe = "A requisição contém um ou mais dados inválidos. Preencha corretamente e tente novamente.";

        List<ApiError.ParametroInvalido> erros = bindingResult.getAllErrors()
            .stream()
            .map(error -> {
                var mensagem = mensagemInternacionalizada.getMessage(error, LocaleContextHolder.getLocale());

                var name = error.getObjectName();

                if (error instanceof FieldError) {
                    name = ((FieldError) error).getField();
                }

                return ApiError.ParametroInvalido.builder()
                    .anotacaoViolada(error.getCode())
                    .localDeErro(name)
                    .motivo(mensagem)
                    .build();
            })
            .toList();

        var problema = this.criarMensagemDeRetorno(tipoDeErroEnum, httpStatus, detalhe)
            .parametrosInvalidos(erros)
            .build();

        return handleExceptionInternal(exception, problema, headers, status, request);
    }
}

