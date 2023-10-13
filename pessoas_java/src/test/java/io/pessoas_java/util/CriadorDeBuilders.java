package io.pessoas_java.util;

import com.github.javafaker.Faker;
import io.pessoas_java.adapters.in.dto.request.PessoaDtoIn;
import io.pessoas_java.adapters.out.entity.PessoaEntity;

import java.time.LocalDate;
import java.util.UUID;

public class CriadorDeBuilders {

    public static Faker faker = new Faker();

    public static CpfGenerator cpfGenerator = new CpfGenerator();

    public static PessoaDtoIn.PessoaDtoInBuilder gerarPessoaDtoInBuilder() {

        return PessoaDtoIn.builder()
            .nome(faker.name().firstName())
            .sobrenome(faker.name().lastName())
            .cpf(cpfGenerator.cpf(false))
            .dataNascimento("01/01/2020")
            .sexo(faker.dog().gender())
            .genero(faker.lorem().characters(5, 10))
            .nivelEducacional("Superior")
            .nacionalidade(faker.lorem().characters(5, 10));
    }

    public static PessoaEntity.PessoaEntityBuilder gerarPessoaEntityBuilder() {

        return PessoaEntity.builder()
            .chave(UUID.randomUUID())
            .nome(faker.name().firstName())
            .sobrenome(faker.name().lastName())
            .cpf(cpfGenerator.cpf(false))
            .dataNascimento("02/02/2021")
            .sexo(faker.dog().gender())
            .genero(faker.lorem().characters(5, 10))
            .nivelEducacional("Especialista")
            .nacionalidade(faker.lorem().characters(5, 10));
    }
}

