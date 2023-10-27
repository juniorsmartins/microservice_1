package io.pessoas_java.adapters.out.repository;

import io.pessoas_java.adapters.out.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    UserEntity findByUsername(String username);

    @Query("SELECT u FROM UserEntity WHERE u.userName =:userName")
    UserEntity findByUserName(@Param("userName") String userName);
}

