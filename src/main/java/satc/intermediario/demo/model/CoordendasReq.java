package satc.intermediario.demo.model;

// Esse record serve para encapsular os dados de requisição para a API de Geocoding
public record CoordendasReq(String cidade, int forecastDays) {
}

