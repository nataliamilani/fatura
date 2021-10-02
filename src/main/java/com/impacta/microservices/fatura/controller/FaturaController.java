package com.impacta.microservices.fatura.controller;

import com.impacta.microservices.fatura.domain.Fatura;

import com.impacta.microservices.fatura.domain.FaturaAtualizaValorRequest;
import com.impacta.microservices.fatura.service.FaturaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.UnknownHostException;

@Tag(name="Fatura endpoint")
@RestController
@RequestMapping("/fatura")
public class FaturaController {

    @Autowired
    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping(path = "/status_aplicacao")
    public String checarStatusAplicacao(){
        return "Aplicação online";
    }

    @PostMapping
    public Fatura criarFatura(@RequestBody Fatura fatura){
        return faturaService.criarFatura(fatura);
    }

    //GET PARA CONSULTAR FATURA POR CONTA, MES E ANO
    @GetMapping(path = "/consulta/{contaId}/{mes}/{ano}")
    public Fatura consultaFaturaContaIdMesAno(@PathVariable("contaId") Integer contaId,
                                              @PathVariable("mes") String mes,
                                              @PathVariable("ano") String ano) throws UnknownHostException {

        return  faturaService.consultaFaturaContaIdMesAno(contaId, mes, ano);

    }

    //PUT PARA PAGAR FATURA
    @PutMapping (path = "/pagar/{contaId}/{mes}/{ano}", produces = { "application/json" })
    public ResponseEntity<Fatura> pagarFaturaContaIdMesAnoValor(@PathVariable("contaId") Integer contaId,
                                                      @PathVariable("mes") String mes,
                                                      @PathVariable("ano") String ano,
                                                      @Valid @RequestBody FaturaAtualizaValorRequest valorPagar) throws UnknownHostException {

        Fatura atualizaPagmentoFatura = faturaService.pagarFaturaContaIdMesAnoValor(contaId, mes, ano, valorPagar.getValorFaturaAtualiza());

        return new ResponseEntity<Fatura>(atualizaPagmentoFatura, HttpStatus.OK);

    }
    
}
