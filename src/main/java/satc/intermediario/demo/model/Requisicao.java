package satc.intermediario.demo.model;

public class Requisicao {
    private Integer latitude;
    private Integer longitude;
    private Integer qtdDias;

    public Requisicao(Integer latitude, Integer longitude, Integer qtdDias) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.qtdDias = qtdDias;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }
}
