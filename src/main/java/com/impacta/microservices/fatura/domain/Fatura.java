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

    @Column(name = "mes")
    private String mes;

    @Column(name = "ano")
    private String ano;

    @Column(name = "valor_fatura")
    private Double valorFatura;

    @Column(name = "status_fatura")
    private String statusFatura;

    public Fatura() {
        super();
    }

    @JsonCreator
    public Fatura(@JsonProperty("id_fatura") Integer idFatura,
                  @JsonProperty("conta_id") Integer contaId,
                  @JsonProperty("mes") String mes,
                  @JsonProperty("ano") String ano,
                  @JsonProperty("valor_fatura") Double valorFatura,
                  @JsonProperty("status_fatura") String statusFatura){
        this.idFatura = idFatura;
        this.contaId = contaId;
        this.mes = mes;
        this.ano = ano;
        this.valorFatura = valorFatura;
        this.statusFatura = statusFatura;
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
}
