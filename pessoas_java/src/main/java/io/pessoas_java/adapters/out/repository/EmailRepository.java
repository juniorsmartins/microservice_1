package io.pessoas_java.adapters.out.repository;

import io.pessoas_java.adapters.out.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> { }

