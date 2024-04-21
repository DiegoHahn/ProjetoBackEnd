package satc.intermediario.demo.model;

//Esse record serve para encapsular os dados de requisição para a API de previsão do tempo
public record PrevisaoReq(double latitude, double longitude, int forecastDays) {
}
