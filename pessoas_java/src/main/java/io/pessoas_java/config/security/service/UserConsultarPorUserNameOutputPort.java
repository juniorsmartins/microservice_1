package io.pessoas_java.config.security.service;

import io.pessoas_java.application.core.domain.User;

import java.util.Optional;

public interface UserConsultarPorUserNameOutputPort {

    Optional<User> findByUserName(String userName);
}

