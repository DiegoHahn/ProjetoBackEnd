package satc.intermediario.demo.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PrevisaoCache {
    //classe para armazenar as previsões pois não estou persistindo em um banco de dados
    private List<PrevisaoMensagem> previsao;

    public void armazenarPrevisao(List<PrevisaoMensagem> previsao) {
        this.previsao = previsao;
    }

    public Optional<List<PrevisaoMensagem>> obterPrevisao() {
        return Optional.ofNullable(previsao);
    }
}
