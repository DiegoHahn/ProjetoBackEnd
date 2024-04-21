package satc.intermediario.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//A resposta da API de previsão traz um JSON com varios dados mas só é necessario o que esta dentro de "daily" por isso a anotação @JsonIgnoreProperties e a criação de uma classe interna (para desserializar o JSON)
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos extras no JSON
public class PrevisaoResp {

    private DadosPrevisao daily;

    public DadosPrevisao getDaily() {
        return daily;
    }

    public static class DadosPrevisao {
        @JsonProperty("time")
        private List<String> dias;
        @JsonProperty("temperature_2m_max")
        private List<Double> temperaturaMax;
        @JsonProperty("temperature_2m_min")
        private List<Double> temperaturaMin;
        @JsonProperty("precipitation_sum")
        private List<Double> volumeChuva;
        @JsonProperty("precipitation_probability_max")
        private List<Integer> probabilidadeChuva;

        public List<String> getDias() {
            return dias;
        }

        public List<Double> getTemperaturaMax() {
            return temperaturaMax;
        }

        public List<Double> getTemperaturaMin() {
            return temperaturaMin;
        }

        public List<Double> getVolumeChuva() {
            return volumeChuva;
        }

        public List<Integer> getProbabilidadeChuva() {
            return probabilidadeChuva;
        }
    }
}
