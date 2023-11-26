package io.micro_texto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MicroNoticiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroNoticiaApplication.class, args);
	}
}

