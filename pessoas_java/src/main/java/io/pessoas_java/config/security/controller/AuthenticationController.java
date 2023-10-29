package io.pessoas_java.config.security.controller;

import io.pessoas_java.config.security.dto.AuthenticationDto;
import io.pessoas_java.config.security.dto.RegisterDto;
import io.pessoas_java.config.security.entity.UserEntity;
import io.pessoas_java.config.security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDto dto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity
            .ok()
            .build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDto registerDto) {

        if (this.userRepository.findByLogin(registerDto.login()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        var newUser = new UserEntity(registerDto.login(), encryptedPassword, registerDto.role());

        this.userRepository.save(newUser);

        return ResponseEntity
            .ok()
            .build();
    }
}

