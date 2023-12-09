package io.micronoticias.adapter.out.repository;

import io.micronoticias.adapter.out.entity.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long> { }

