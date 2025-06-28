package io.github.ajudabrasil.apiajudabrasil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiAjudaBrasilApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAjudaBrasilApplication.class, args);
		String senhaParaGerarHash = "teste123";
		String hash = passwordEndecoder.gerar(senhaParaGerarHash);

		System.out.println("Hash gerado para a senha '" + senhaParaGerarHash + "':");
		System.out.println("##" + hash);
		System.out.println("Copie o hash acima e cole no seu banco de dados.");

	}

}
