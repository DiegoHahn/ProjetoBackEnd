package satc.intermediario.demo.model;
import reactor.core.publisher.Mono;

public interface CoordenadasInterface {
    Mono<String> getDados(String nomeCidade);
}
