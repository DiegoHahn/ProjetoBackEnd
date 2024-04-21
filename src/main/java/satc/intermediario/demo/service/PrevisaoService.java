package satc.intermediario.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import satc.intermediario.demo.model.PrevisaoMensagem;
import satc.intermediario.demo.model.PrevisaoReq;
import satc.intermediario.demo.model.PrevisaoResp;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrevisaoService {

    private static final Logger logger = LoggerFactory.getLogger(PrevisaoService.class);

    @Autowired
    private WebClient webClientPrevisao;

    //Faz a requisição para a API de previsão do tempo com as coordenadas e a quantidade de dias de previsão
    public Mono<List<PrevisaoMensagem>> obterPrevisao(PrevisaoReq previsaoReq) {
        return webClientPrevisao.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", previsaoReq.latitude())
                        .queryParam("longitude", previsaoReq.longitude())
                        .queryParam("daily", "temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_probability_max") //Parametros fixos (poderiam ser enviados outros também) que a API de previsão do tempo pede
                        .queryParam("timezone", "America/Sao_Paulo")//Fixo também
                        .queryParam("forecast_days", previsaoReq.forecastDays())
                        .build())
                .retrieve()
                .bodyToMono(PrevisaoResp.class)
                .map(resp -> {
                    //Gera uma lista de mensagens com os dados da previsão do tempo para cada dia (eu fiz isso para mostrar um dia por linha no navegador e não tudo em uma linha só)
                    List<PrevisaoMensagem> mensagens = new ArrayList<>();
                    //Um loop que percorre o tamanho da lista de dias que pode variar
                    for (int i = 0; i < resp.getDaily().getDias().size(); i++) {
                        String mensagem = String.format("Dia: %s, Temperatura mínima: %.1f°C, Temperatura máxima: %.1f°C, " +
                                        "Probabilidade Máxima de chover: %d%%, Volume de chuva previsto: %.2f mm",
                                resp.getDaily().getDias().get(i),
                                resp.getDaily().getTemperaturaMin().get(i),
                                resp.getDaily().getTemperaturaMax().get(i),
                                resp.getDaily().getProbabilidadeChuva().get(i),
                                resp.getDaily().getVolumeChuva().get(i));
                        mensagens.add(new PrevisaoMensagem(mensagem));
                    }
                    return mensagens;
                });
    }
}
