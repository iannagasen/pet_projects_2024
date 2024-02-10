package dev.agasen.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.agasen.authserver.client.ClientRepository;

@SpringBootApplication
public class AuthServerApplication {

	@Autowired private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner cLineRunner() {
		return args -> {
			System.out.println("count ---> " + clientRepository.count());
		};
	}

}
