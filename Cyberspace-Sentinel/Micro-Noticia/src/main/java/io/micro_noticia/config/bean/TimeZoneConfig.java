package io.micro_noticia.config.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class TimeZoneConfig {

    @PostConstruct
    public void timezoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}

