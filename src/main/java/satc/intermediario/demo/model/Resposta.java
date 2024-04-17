package satc.intermediario.demo.model;

public class Resposta {
    private String data;
    private final String descricao;
    private String temperaturaMaxima;
    private String temperaturaMinima;
    private String probabilidadeChuva;
    private String quantidadeChuva;


    public Resposta(String data, String descricao, String temperaturaMaxima, String temperaturaMinima, String probabilidadeChuva, String quantidadeChuva) {
        this.data = data;
        this.descricao = descricao;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
    }

    public String getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(String temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public String getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(String temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProbabilidadeChuva() {
        return probabilidadeChuva;
    }

    public void setProbabilidadeChuva(String probabilidadeChuva) {
        this.probabilidadeChuva = probabilidadeChuva;
    }

    public String getQuantidadeChuva() {
        return quantidadeChuva;
    }

    public void setQuantidadeChuva(String quantidadeChuva) {
        this.quantidadeChuva = quantidadeChuva;
    }

    public String getDescricao() {
        return descricao;
    }
}
