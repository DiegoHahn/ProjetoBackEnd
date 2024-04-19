package satc.intermediario.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import satc.intermediario.demo.service.CoordenadasService;

@Controller
public class CoordenadasController {

    @Autowired
    private CoordenadasService coordenadasService;

    public Mono<String> getCoordinates(String cidade) {
        return coordenadasService.getDados(cidade);
    }
}
