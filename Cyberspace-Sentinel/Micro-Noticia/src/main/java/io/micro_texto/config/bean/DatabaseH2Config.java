package io.micro_texto.config.bean;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
public class DatabaseH2Config {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:h2:mem:banco")
            .driverClassName("org.h2.Driver")
            .username("sa")
            .password("")
            .build();
    }
}

