package com.impacta.microservices.fatura.client.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultaContaCorrente {

    private Integer id_cc;
    private Integer contaId;
    private Double saldo;
    private Integer clienteId;

    public ConsultaContaCorrente() {
    }

    @JsonCreator
    public ConsultaContaCorrente(@JsonProperty("id_cc") Integer id_cc,
                                 @JsonProperty("conta_id") Integer contaId,
                                 @JsonProperty("cliente_id") Integer clienteId,
                                 @JsonProperty("saldo") Double saldo) {
        this.id_cc = id_cc;
        this.contaId = contaId;
        this.clienteId = clienteId;
        this.saldo = saldo;
    }

    public Integer getId_cc() {
        return id_cc;
    }

    public void setId_cc(Integer id_cc) {
        this.id_cc = id_cc;
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }


}
