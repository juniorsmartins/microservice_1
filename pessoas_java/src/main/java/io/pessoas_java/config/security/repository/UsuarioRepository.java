package io.pessoas_java.config.security.repository;

import io.pessoas_java.config.security.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByUsername(String username);

    @Query("select u.role from Usuario u where u.username like :username")
    Optional<UsuarioEntity.Role> findRoleByUsername(String username);
}

