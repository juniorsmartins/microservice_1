package io.pessoas_java.application.ports.out;

import java.util.UUID;

public interface PessoaDeletarPorChaveOutputPort {

    void deletarPorChave(UUID chave);
}

