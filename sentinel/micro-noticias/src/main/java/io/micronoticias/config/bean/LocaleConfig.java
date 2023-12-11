package io.micronoticias.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();

        List<Locale> supportedLocales = new ArrayList<>();
        supportedLocales.add(Locale.US);
        supportedLocales.add(Locale.forLanguageTag("pt-BR"));
        supportedLocales.add(Locale.forLanguageTag("es"));

        localeResolver.setSupportedLocales(supportedLocales);

        return  localeResolver;
    }
}

