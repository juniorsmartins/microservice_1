package io.pessoas_java.config.security.repository;

import io.pessoas_java.adapters.out.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity WHERE u.userName =:userName")
    Optional<UserEntity> findByUserName(@Param("userName") String userName);
}

