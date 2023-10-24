package io.pessoas_java.application.core.domain.utils;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

public final class UtilImpl implements Util {

    @Override
    public String capitalizar(String texto) {

        return Optional.of(texto)
            .map(String::trim)
            .map(WordUtils::capitalizeFully)
            .map(nome -> RegExUtils.replaceAll(nome, " Da ", " da "))
            .map(nome -> RegExUtils.replaceAll(nome, " De ", " de "))
            .map(nome -> RegExUtils.replaceAll(nome, " Di ", " di "))
            .map(nome -> RegExUtils.replaceAll(nome, " Do ", " do "))
            .map(nome -> RegExUtils.replaceAll(nome, " Du ", " du "))
            .map(nome -> RegExUtils.replaceAll(nome, " Em ", " em "))
            .map(nome -> RegExUtils.replaceAll(nome, " Um ", " um "))
            .map(nome -> RegExUtils.replaceAll(nome, " Na ", " na "))
            .map(nome -> RegExUtils.replaceAll(nome, " Ne ", " ne "))
            .map(nome -> RegExUtils.replaceAll(nome, " Ni ", " ni "))
            .map(nome -> RegExUtils.replaceAll(nome, " No ", " no "))
            .map(nome -> RegExUtils.replaceAll(nome, " Nu ", " nu "))
            .map(nome -> RegExUtils.replaceAll(nome, "Da ", "da "))
            .map(nome -> RegExUtils.replaceAll(nome, "De ", "de "))
            .orElseThrow(NoSuchElementException::new);
    }
}

