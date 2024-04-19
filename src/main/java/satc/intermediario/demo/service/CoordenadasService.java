package satc.intermediario.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import satc.intermediario.demo.model.CoordenadasInterface;

@Service
public class CoordenadasService implements CoordenadasInterface {

    @Autowired
    private WebClient webClientCoordenadas;

    //Mono funciona como um optional, pode ter um valor ou não
    //Faz a requisição para a api e retorna um objeto {results[dados], códigoDaReq}
    public Mono<String> getDados(String nomeCidade) {
        return webClientCoordenadas.get().uri(uriBuilder -> uriBuilder
                        .path("/v1/search")
                        .queryParam("name", nomeCidade)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(this::processaResposta);
    }

    //Processamento dos dados da resposta e tratamento de erro
    private Mono<String> processaResposta(String response) {
        try {
            //Desserealizando a resposta com JsonNode da biblioteca Jackson
            JsonNode dadosCidade = new ObjectMapper().readTree(response);

            //A resposta sempre traz o código da requisição, então o erro é só quando essa resposta não tem o campo results
            if (!dadosCidade.has("results")) {
                return Mono.just("Nenhuma cidade encontrada com o nome fornecido.");
            }
            //
            //
            ///mudar aqui pra passar as coordenadas pra um array depois
            //
            //
            //
            JsonNode firstResult = dadosCidade.get("results").get(0);
            String coordinates = "Latitude: " + firstResult.get("latitude").asText() +
                    ", Longitude: " + firstResult.get("longitude").asText();
            return Mono.just(coordinates);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Erro inesperado", e));
        }
    }

}
