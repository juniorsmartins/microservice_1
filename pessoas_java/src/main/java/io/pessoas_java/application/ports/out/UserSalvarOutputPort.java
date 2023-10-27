package io.pessoas_java.application.ports.out;

import io.pessoas_java.application.core.domain.User;

public interface UserSalvarOutputPort {

    User salvar(User user);
}

