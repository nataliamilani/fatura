package com.impacta.microservices.fatura.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class SaldoContaCorrente {

    private Double valorContaCorrente;

    @JsonCreator
    public SaldoContaCorrente(@JsonProperty("saldoContaCorrente") Double valorContaCorrente) {
        this.valorContaCorrente = valorContaCorrente;
    }

    public Double getValorContaCorrente() {return valorContaCorrente;}
}
