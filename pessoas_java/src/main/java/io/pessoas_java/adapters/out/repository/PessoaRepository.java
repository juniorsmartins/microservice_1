package io.pessoas_java.adapters.out.repository;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> { }

