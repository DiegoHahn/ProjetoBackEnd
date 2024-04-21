package satc.intermediario.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import satc.intermediario.demo.model.CoordendasReq;
import satc.intermediario.demo.model.PrevisaoReq;

@Service
public class CoordenadasService {

    @Autowired
    private WebClient webClientCoordenadas;

    //Mono funciona como um optional, mas para programação assíncrona
    //Faz a requisição para a api de geocode com o nome da cidade e o forecastdays do coordenadasReq, e retorna um objeto {results[dados], códigoDaReq} com as coordenadas
    public Mono<PrevisaoReq> getDados(CoordendasReq coordenadasReq) {
        String nomeCidade = coordenadasReq.cidade();
        int forecastDays = coordenadasReq.forecastDays();
        return webClientCoordenadas.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/search").queryParam("name", nomeCidade).build())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> processaResposta(response, forecastDays)); //passa o forecastDays para o processaResposta junto com as coordenadas
    }

    private Mono<PrevisaoReq> processaResposta(String response, int forecastDays) {
        try {
            //Desserealizando a resposta com JsonNode da biblioteca Jackson
            JsonNode dadosCidade = new ObjectMapper().readTree(response);

            //A resposta sempre traz o código da requisição, então o erro é só quando essa resposta não tem o campo results
            if (!dadosCidade.has("results")) {
                return Mono.just(new PrevisaoReq(0, 0, 0));
            }
            JsonNode firstResult = dadosCidade.get("results").get(0);
            double latitude = firstResult.get("latitude").asDouble();
            double longitude = firstResult.get("longitude").asDouble();

            //Retorna um objeto PrevisaoReq com as coordenadas e a quantidade de dias de previsão
            return Mono.just(new PrevisaoReq(latitude, longitude, forecastDays));
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Erro inesperado", e));
        }
    }
}
