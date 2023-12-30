package io.micronoticias.adapter.out.repository;

import io.micronoticias.adapter.out.entity.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<NoticiaEntity, Long>,
        JpaSpecificationExecutor<NoticiaEntity>, RevisionRepository<NoticiaEntity, Long, Long> { }

