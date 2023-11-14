package io.pessoas_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class PessoasJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PessoasJavaApplication.class, args);
	}

}
