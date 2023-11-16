package io.pessoas_java.adapters.out.spec;

import io.pessoas_java.adapters.out.entity.PessoaEntity;
import io.pessoas_java.application.core.domain.enums.EstadoCivilEnum;
import io.pessoas_java.application.core.domain.enums.NivelEducacionalEnum;
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
                if (Arrays.stream(SexoEnum.values())
                    .anyMatch(e -> e.name().equalsIgnoreCase(filtro.getSexo()))) {

                    SexoEnum sexoEnum = SexoEnum.valueOf(filtro.getSexo());
                    predicados.add(criteriaBuilder.equal(root.get("sexo"), sexoEnum));
                }
            }

            if (ObjectUtils.isNotEmpty(filtro.getGenero())) {
                predicados.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("genero")), filtro.getGenero().toLowerCase()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getNivelEducacional())) {
                var nivelEducacional = filtro.getNivelEducacional().trim().toUpperCase().replace(" ", "_");

                if (Arrays.stream(NivelEducacionalEnum.values())
                    .anyMatch(e -> e.name().equalsIgnoreCase(nivelEducacional))) {

                    NivelEducacionalEnum nivelEducacionalEnum = NivelEducacionalEnum.valueOf(nivelEducacional);
                    predicados.add(criteriaBuilder.equal(root.get("nivelEducacional"), nivelEducacionalEnum));
                }
            }

            if (ObjectUtils.isNotEmpty(filtro.getNacionalidade())) {
                predicados.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("nacionalidade")), filtro.getNacionalidade().toLowerCase()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getEstadoCivil())) {
                var estadoCivil = filtro.getEstadoCivil().trim().toUpperCase().replace(" ", "_");

                if (Arrays.stream(EstadoCivilEnum.values())
                    .anyMatch(e -> e.name().equalsIgnoreCase(estadoCivil))) {

                    EstadoCivilEnum estadoCivilEnum = EstadoCivilEnum.valueOf(estadoCivil);
                    predicados.add(criteriaBuilder.equal(root.get("estadoCivil"), estadoCivilEnum));
                }
            }

            if (ObjectUtils.isNotEmpty(filtro.getTelefones()) && ObjectUtils.isNotEmpty(filtro.getTelefones().getNumero())) {
                predicados.add(criteriaBuilder.equal(root.join("telefones").get("numero"), filtro.getTelefones().getNumero()));
            }

            if (ObjectUtils.isNotEmpty(filtro.getEmails()) && ObjectUtils.isNotEmpty(filtro.getEmails().getEmail())) {
                predicados.add(criteriaBuilder.equal(root.join("emails").get("email"), filtro.getEmails().getEmail()));
            }

            return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
        });
    }
}

