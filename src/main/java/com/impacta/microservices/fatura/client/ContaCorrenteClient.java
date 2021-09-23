package com.impacta.microservices.fatura.client;
import com.impacta.microservices.fatura.client.response.SaldoContaCorrente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "conta-corrente", url = "${clients.contacorrente}")
public interface ContaCorrenteClient {

    @GetMapping(value = "/saldo/{contaId}")
    SaldoContaCorrente getSaldoContaCorrente(@PathVariable("contaId") Integer contaId);

}
