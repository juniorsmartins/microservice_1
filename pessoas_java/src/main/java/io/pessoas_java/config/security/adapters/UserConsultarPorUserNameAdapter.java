package io.pessoas_java.config.security.adapters;

import io.pessoas_java.adapters.out.UserSalvarAdapter;
import io.pessoas_java.application.core.domain.User;
import io.pessoas_java.config.security.converter.UserEntityMapper;
import io.pessoas_java.config.security.repository.UserRepository;
import io.pessoas_java.config.security.service.UserConsultarPorUserNameOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Component
public class UserConsultarPorUserNameAdapter implements UserConsultarPorUserNameOutputPort {

    private final Logger logger = Logger.getLogger(UserSalvarAdapter.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUserName(final String userName) {

        logger.info("Adapter - iniciado processo de buscar um User no banco de dados.");

        var userEncontrado = this.userRepository.findByUserName(userName)
            .map(this.userEntityMapper::toUser);

        logger.info("Adapter - finalizado processo de buscar um User no banco de dados.");

        return userEncontrado;
    }
}

