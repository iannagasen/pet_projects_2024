package dev.agasen.webfluxpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.agasen.webfluxpatterns.sec02")
public class WebfluxPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxPatternsApplication.class, args);
	}

}
