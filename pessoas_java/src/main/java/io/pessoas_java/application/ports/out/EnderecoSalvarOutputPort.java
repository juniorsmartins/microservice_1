package io.pessoas_java.application.ports.out;

import io.pessoas_java.application.core.domain.value_object.Endereco;

public interface EnderecoSalvarOutputPort {

    Endereco salvar(Endereco endereco);
}

