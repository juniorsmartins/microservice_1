package io.micronoticias.adapter.out.specification;

import io.micronoticias.adapter.out.entity.NoticiaEntity;
import io.micronoticias.application.core.domain.filtro.NoticiaFiltro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public final class NoticiaSpecification {

    public static Specification<NoticiaEntity> consultarDinamicamente(NoticiaFiltro filtro) {

        return ((root, query, criteriaBuilder) -> {

            var pesquisa = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.getId())) {
                adicionarIdPredicados(filtro.getId(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getChapeu())) {
                adicionarChapeuPredicados(filtro.getChapeu(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getTitulo())) {
                adicionarTituloPrecidados(filtro.getTitulo(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getLinhaFina())) {
                adicionarLinhaFinaPredicados(filtro.getLinhaFina(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getLide())) {
                adicionarLidePredicados(filtro.getLide(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getCorpo())) {
                adicionarCorpoPredicados(filtro.getCorpo(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getNomeAutor())) {
                adicionarNomeAutorPredicados(filtro.getNomeAutor(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getFonte())) {
                adicionarFontePredicados(filtro.getFonte(), root, criteriaBuilder, pesquisa);
            }

            return criteriaBuilder.and(pesquisa.toArray(new Predicate[0]));
        });
    }

    private static void adicionarIdPredicados(String id, Root<NoticiaEntity> root,
                                              CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var predicates = Stream.of(id.trim().split(","))
                .map(Long::parseLong)
                .map(valor -> criteriaBuilder.equal(root.get("id"), valor))
                .toList();

//        var predicates = parametros.stream()
//                .map(valor -> criteriaBuilder.equal(root.get("id"), valor))
//                .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarChapeuPredicados(String chapeu, Root<NoticiaEntity> root,
                                                  CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Arrays.asList(chapeu.trim().split(","));

        List<Predicate> predicates = parametros.stream()
            .map(valor -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("chapeu")), "%" + valor.toLowerCase() + "%"))
            .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarTituloPrecidados(String titulo, Root<NoticiaEntity> root, CriteriaBuilder criteriaBuilder,
                                                  List<Predicate> pesquisa) {

        var parametros = Arrays.asList(titulo.trim().split(","));

        List<Predicate> predicates = parametros.stream()
                .map(valor -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("titulo")), "%" + valor.toLowerCase() + "%"))
                .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarLinhaFinaPredicados(String linhaFina, Root<NoticiaEntity> root,
                                             CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Arrays.asList(linhaFina.trim().split(","));

        List<Predicate> predicates = parametros.stream()
                .map(valor -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("linhaFina")), "%" + valor.toLowerCase() + "%"))
                .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarLidePredicados(String lide, Root<NoticiaEntity> root,
                                                CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Arrays.asList(lide.trim().split(","));

        List<Predicate> predicates = parametros.stream()
                .map(valor -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lide")), "%" + valor.toLowerCase() + "%"))
                .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarCorpoPredicados(String corpo, Root<NoticiaEntity> root,
                                                 CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Arrays.asList(corpo.trim().split(","));

        List<Predicate> predicates = parametros.stream()
                .map(valor -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("corpo")), "%" + valor.toLowerCase() + "%"))
                .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarNomeAutorPredicados(String nomeAutor, Root<NoticiaEntity> root,
                                                     CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Arrays.asList(nomeAutor.trim().split(","));

        List<Predicate> predicates = parametros.stream()
                .map(valor -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nomeAutor")), "%" + valor.toLowerCase() + "%"))
                .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarFontePredicados(String fonte, Root<NoticiaEntity> root,
                                                 CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Arrays.asList(fonte.trim().split(","));

        List<Predicate> predicates = parametros.stream()
                .map(valor -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("fonte")), "%" + valor.toLowerCase() + "%"))
                .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }
}

