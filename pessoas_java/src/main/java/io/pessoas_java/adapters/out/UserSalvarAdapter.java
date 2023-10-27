package io.pessoas_java.adapters.out;

import io.pessoas_java.config.security.converter.UserEntityMapper;
import io.pessoas_java.config.security.repository.UserRepository;
import io.pessoas_java.application.core.domain.User;
import io.pessoas_java.application.ports.out.UserSalvarOutputPort;
import io.pessoas_java.config.exceptions.http_500.FailedToSaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class UserSalvarAdapter implements UserSalvarOutputPort {

    private final Logger logger = Logger.getLogger(UserSalvarAdapter.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Transactional
    @Override
    public User salvar(User user) {

        logger.info("Adapter - iniciado processo de salvar um User.");

        var userSalvo = Optional.of(user)
            .map(this.userEntityMapper::toUserEntity)
            .map(this.userRepository::save)
            .map(this.userEntityMapper::toUser)
            .orElseThrow(FailedToSaveException::new);

        logger.info("Adapter - finalizado processo de salvar um User.");

        return userSalvo;
    }
}

