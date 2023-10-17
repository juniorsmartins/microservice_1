package io.pessoas_java.adapters.out.repository;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long>,
        JpaSpecificationExecutor<PessoaEntity> { }

