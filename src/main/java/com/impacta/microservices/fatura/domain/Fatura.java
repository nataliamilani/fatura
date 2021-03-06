package com.impacta.microservices.fatura.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "fatura")
public class Fatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fatura")
    private Integer idFatura;

    @Column(name = "conta_id")
    private Integer contaId;

    @Column(name = "cliente_id")
    private Integer clienteId;

    @Column(name = "mes")
    private String mes;

    @Column(name = "ano")
    private String ano;

    @Column(name = "valor_fatura")
    private Double valorFatura;

    @Column(name = "status_fatura")
    private String statusFatura;

    @Column(name = "status_pagamento")
    private String statusPagamento;


    public Fatura() {
        super();
    }

    @JsonCreator
    public Fatura(@JsonProperty("conta_id") Integer contaId,
                  @JsonProperty("cliente_id") Integer clienteId,
                  @JsonProperty("mes") String mes,
                  @JsonProperty("ano") String ano,
                  @JsonProperty("valor_fatura") Double valorFatura,
                  @JsonProperty("status_fatura") String statusFatura,
                  @JsonProperty("status_pagamento") String statusPagamento){
        this.contaId = contaId;
        this.clienteId = clienteId;
        this.mes = mes;
        this.ano = ano;
        this.valorFatura = valorFatura;
        this.statusFatura = statusFatura;
        this.statusPagamento = statusPagamento;
    }

    public Integer getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(Integer idFatura) {
        this.idFatura = idFatura;
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Double getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(Double valorFatura) {
        this.valorFatura = valorFatura;
    }

    public String getStatusFatura() {
        return statusFatura;
    }

    public void setStatusFatura(String statusFatura) {
        this.statusFatura = statusFatura;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
