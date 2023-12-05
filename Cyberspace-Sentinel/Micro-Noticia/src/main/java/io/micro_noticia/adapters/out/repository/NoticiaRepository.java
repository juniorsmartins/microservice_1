package io.micro_noticia.adapters.out.repository;

import io.micro_noticia.adapters.out.entity.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long>,
        RevisionRepository<NoticiaEntity, Long, Long> { }

