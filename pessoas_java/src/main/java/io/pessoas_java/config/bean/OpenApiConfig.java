package io.pessoas_java.config.bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Restful API com Java 21 e Spring Boot 3.1.4.")
                .version("v1")
                .description("API de microsserviço para registro de pessoas.")
                .termsOfService("http://teste.com.br/terms-of-service") // Colocarei uma url fictícia. O certo é colocar uma que leve aos termos.
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
                .contact(new Contact()
                    .name("Junior Martins")
                    .email("teste@email.com")
                    .url("https://www.linkedin.com/in/juniorsmartins/"))
            );
    }
}

