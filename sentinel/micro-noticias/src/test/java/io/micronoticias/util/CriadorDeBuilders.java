package io.micronoticias.util;

import com.github.javafaker.Faker;
import io.micronoticias.adapter.in.dto.request.NoticiaCriarDtoIn;

public final class CriadorDeBuilders {

    public static Faker faker = new Faker();

    public static NoticiaCriarDtoIn.NoticiaCriarDtoInBuilder gerarNoticiaCriarDtoInBuilder() {

        return NoticiaCriarDtoIn.builder()
                .chapeu(faker.lorem().characters(5, 20))
                .titulo(faker.lorem().characters(5, 100))
                .linhaFina(faker.lorem().characters(5, 150))
                .lide(faker.lorem().characters(300, 500))
                .corpo(faker.lorem().characters(3000, 5000))
                .nomeAutor(faker.lorem().characters(5, 50))
                .fonte(faker.lorem().characters(5, 250));
    }
}

