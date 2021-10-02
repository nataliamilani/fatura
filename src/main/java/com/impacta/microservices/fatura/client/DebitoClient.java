package com.impacta.microservices.fatura.client;
import com.impacta.microservices.fatura.client.response.CriarDebito;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "debito", url = "${clients.debito}")
public interface DebitoClient {

    @PostMapping()
    CriarDebito criarDebito(@RequestBody CriarDebito debitoRequest);

}