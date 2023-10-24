package io.pessoas_java.config.bean;

import io.pessoas_java.application.core.domain.utils.UtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public UtilImpl utilImpl() {
        return new UtilImpl();
    }
}

