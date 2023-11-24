package io.pessoas_java.adapters.out.repository;

import io.pessoas_java.adapters.out.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> { }

