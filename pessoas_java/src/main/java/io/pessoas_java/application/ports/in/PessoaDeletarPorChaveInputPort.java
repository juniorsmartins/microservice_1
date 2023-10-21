package io.pessoas_java.application.ports.in;

import java.util.UUID;

public interface PessoaDeletarPorChaveInputPort {

    void deletar(UUID chave);
}

