package com.impacta.microservices.fatura.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FaturaAtualizaValorRequest {

    private Double valorFaturaAtualiza;

    @JsonCreator
    public FaturaAtualizaValorRequest(@JsonProperty("valor_fatura") Double valorFaturaAtualiza) {
        this.valorFaturaAtualiza = valorFaturaAtualiza;
    }

    public Double getValorFaturaAtualiza() {
        return valorFaturaAtualiza;
    }

    public void setValorFaturaAtualiza(Double valorFaturaAtualiza) {
        this.valorFaturaAtualiza = valorFaturaAtualiza;
    }

}
