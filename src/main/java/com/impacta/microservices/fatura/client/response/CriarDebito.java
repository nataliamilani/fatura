package com.impacta.microservices.fatura.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CriarDebito {

    private Integer contaId;
    private Double valorDebito;
    private Integer clienteId;
    private String tipoConta;

    @JsonCreator
    public CriarDebito(@JsonProperty("conta_id") Integer contaId,
                       @JsonProperty("valor_debito") double valorDebito,
                       @JsonProperty("cliente_id") Integer clienteId,
                       @JsonProperty("tipo_conta") String tipoConta) {
        this.contaId = contaId;
        this.valorDebito = valorDebito;
        this.clienteId = clienteId;
        this.tipoConta = tipoConta;
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public Double getValorDebito() {
        return valorDebito;
    }

    public void setValorDebito(Double valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

}
