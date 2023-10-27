package io.pessoas_java.config.security.usecase;

import io.pessoas_java.config.exceptions.http_404.UserNaoEncontradoPorUserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UserConsultarPorUserNameUseCase implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserConsultarPorUserNameUseCase.class.getName());

    @Autowired
    private UserConsultarPorUserNameOutputPort userConsultarPorUserNameOutputPort;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {

        logger.info("UseCase - iniciado serviço de consultar User por userName: " + userName);

        var user = this.userConsultarPorUserNameOutputPort.findByUserName(userName)
            .orElseThrow(() -> new UserNaoEncontradoPorUserNameException(userName));

        logger.info("UseCase - finalizado serviço de consultar User por userName.");

        return user;
    }
}

