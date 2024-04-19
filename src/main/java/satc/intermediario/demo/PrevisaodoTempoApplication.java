package satc.intermediario.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PrevisaodoTempoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrevisaodoTempoApplication.class, args);
	}

	//webclient para fazer as requisições
	@Bean
	public WebClient webClientCoordenadas() {
		return WebClient.create("https://geocoding-api.open-meteo.com");
	}
}
