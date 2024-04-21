package satc.intermediario.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import satc.intermediario.demo.model.CoordendasReq;
import satc.intermediario.demo.model.DadosAluno;
import satc.intermediario.demo.model.PrevisaoCache;
import satc.intermediario.demo.model.PrevisaoMensagem;
import satc.intermediario.demo.service.CoordenadasService;
import satc.intermediario.demo.service.PrevisaoService;

import java.util.List;

@RestController
public class PrevisaoController {

    @Autowired
    private CoordenadasService coordenadasService;

    @Autowired
    private PrevisaoService previsaoService;

    @Autowired
    private PrevisaoCache previsaoCache;

    // Método que recebe as coordenadas e faz um encadeamentro de chamadas nos serviços para manter os dados no fluxo
    @PostMapping("/coordenadas")
    public Mono<ResponseEntity<String>> iniciarPrevisao(@RequestBody CoordendasReq coordenadasReq) {
        return coordenadasService.getDados(coordenadasReq)
                .flatMap(previsaoReq -> previsaoService.obterPrevisao(previsaoReq))
                .flatMap(previsao -> {
                    // Se não houver previsões, retorna uma mensagem de erro, caso contrário, armazena a previsão na classe de cache
                    if (previsao.isEmpty()) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontradas previsões para a cidade fornecida."));
                    } else {
                        previsaoCache.armazenarPrevisao(previsao);
                        return Mono.just(ResponseEntity.ok("Previsão processada e armazenada com sucesso."));
                    }
                });
    }

    // Método que retorna as previsões armazenadas no cache
    @GetMapping("/previsao")
    public Mono<List<PrevisaoMensagem>> obterPrevisao() {
        return Mono.justOrEmpty(previsaoCache.obterPrevisao())
                .switchIfEmpty(Mono.error(new RuntimeException("Nenhuma previsão disponível")));
    }

    @GetMapping("/ajuda")
    public DadosAluno ajuda() {
        return new DadosAluno();
    }
}