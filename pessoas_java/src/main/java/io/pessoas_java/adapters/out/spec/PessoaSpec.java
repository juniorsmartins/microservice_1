package io.pessoas_java.adapters.out.spec;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.application.core.domain.enums.SexoEnum;
import io.pessoas_java.application.core.domain.filtro.PessoaFiltro;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PessoaSpec {

    public static Specification<PessoaEntity> consultarDinamicamente(PessoaFiltro filtro) {

        return ((root, query, criteriaBuilder) -> {

            var predicados = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.getChave())) {
                predicados.add(criteriaBuilder.equal(root.get("chave"), filtro.getChave()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getNome())) {
                var parametros = Arrays.asList(filtro.getNome().split(","));
                List<Predicate> parametrosPredicados = parametros.stream()
                    .map(parametro -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")), "%" + parametro.toLowerCase() + "%"))
                    .toList();
                predicados.add(criteriaBuilder.or(parametrosPredicados.toArray(new Predicate[0])));
            }

            if (ObjectUtils.isNotEmpty(filtro.getSobrenome())) {
                var parametros = Arrays.asList(filtro.getSobrenome().split(","));
                List<Predicate> parametrosPredicados = parametros.stream()
                    .map(parametro -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("sobrenome")), "%" + parametro.toLowerCase() + "%"))
                    .toList();
                predicados.add(criteriaBuilder.or(parametrosPredicados.toArray(new Predicate[0])));
            }

            if (ObjectUtils.isNotEmpty(filtro.getCpf())) {
                var parametros = Arrays.asList(filtro.getCpf().split(","));
                List<Predicate> parametrosPredicados = parametros.stream()
                    .map(parametro -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("cpf")), "%" + parametro.toLowerCase() + "%"))
                    .toList();
                predicados.add(criteriaBuilder.or(parametrosPredicados.toArray(new Predicate[0])));
            }

            if (ObjectUtils.isNotEmpty(filtro.getSexo())) {
                if (Arrays.stream(SexoEnum.values()).anyMatch(e -> e.name().equalsIgnoreCase(filtro.getSexo()))) {
                    SexoEnum sexoEnum = SexoEnum.valueOf(filtro.getSexo());
                    predicados.add(criteriaBuilder.equal(root.get("sexo"), sexoEnum));
                }
            }

            if (ObjectUtils.isNotEmpty(filtro.getGenero())) {
                predicados.add(criteriaBuilder.equal(root.get("genero"), filtro.getGenero()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getNivelEducacional())) {
                predicados.add(criteriaBuilder.equal(root.get("nivelEducacional"), filtro.getNivelEducacional()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getNacionalidade())) {
                predicados.add(criteriaBuilder.equal(root.get("nacionalidade"), filtro.getNacionalidade()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getEstadoCivil())) {
                predicados.add(criteriaBuilder.equal(root.get("estadoCivil"), filtro.getEstadoCivil()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getTelefone()) && ObjectUtils.isNotEmpty(filtro.getTelefone().getNumero())) {
                predicados.add(criteriaBuilder.like(root.get("telefone.numero"), "%" + filtro.getTelefone().getNumero() + "%"));
            }

            return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
        });
    }
}

