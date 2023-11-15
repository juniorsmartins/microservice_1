package io.pessoas_java.adapters.out.repository;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long>,
        JpaSpecificationExecutor<PessoaEntity> {

    Optional<PessoaEntity> findByChave(UUID chave);

    Optional<PessoaEntity> findByCpf(String cpf);

    boolean existsByChave(UUID chave);

    void deleteByChave(UUID chave);
}

