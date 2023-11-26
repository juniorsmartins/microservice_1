package io.micro_texto.adapters.out.repository;

import io.micro_texto.adapters.out.entity.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long> { }

