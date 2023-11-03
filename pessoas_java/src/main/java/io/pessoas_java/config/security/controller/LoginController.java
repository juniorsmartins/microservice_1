package io.pessoas_java.config.security.controller;

import io.pessoas_java.config.exceptions.ErrorMessage;
import io.pessoas_java.config.security.dto.UsuarioLoginDto;
import io.pessoas_java.config.security.jwt.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/auth")
public class LoginController {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto loginDto, HttpServletRequest request) {

        log.info("Processo de autenticação pelo login '{}'", loginDto.getUsername());

        try {
            var authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            this.authenticationManager.authenticate(authenticationToken);
            var token = this.jwtUserDetailsService.getTokenAuthenticated(loginDto.getUsername());
            return ResponseEntity
                .ok(token);

        } catch (AuthenticationException ex) {
            log.warn("Bad Credentials from username '{}'", loginDto.getUsername());
        }
        return ResponseEntity
            .badRequest()
            .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais inválidas"));
    }
}

