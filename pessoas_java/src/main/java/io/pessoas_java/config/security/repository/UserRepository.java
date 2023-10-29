package io.pessoas_java.config.security.repository;

import io.pessoas_java.config.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserDetails findByLogin(String login);
}

