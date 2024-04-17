package satc.intermediario.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import satc.intermediario.demo.service.RequisicaoService;

@RestController
@RequestMapping("/Requisicao")
public class RequisicaoController {

    private final RequisicaoService accountantService;

    public RequisicaoController(RequisicaoService accountantService) {
        this.accountantService = accountantService;
    }


}