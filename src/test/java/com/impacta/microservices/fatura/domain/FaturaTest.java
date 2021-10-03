package com.impacta.microservices.fatura.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FaturaTest {

    @Test
    public void criarFatura() {

        final Integer contaId = 1500603805;
        final Integer clienteId = 1;
        final String mes = "janeiro";
        final String ano = "2021";
        final Double valorFatura = 5.000;
        final String statusFatura = "Paga";
        final String statusPagamento = "Pagamento Integral";

        Fatura fatura = new Fatura(contaId, clienteId, mes, ano, valorFatura, statusFatura, statusPagamento);

        assertEquals(clienteId, fatura.getClienteId());
        assertEquals(contaId, fatura.getContaId());
        assertEquals(mes, fatura.getMes());
        assertEquals(ano, fatura.getAno());
        assertEquals(valorFatura, fatura.getValorFatura());
        assertEquals(statusFatura, fatura.getStatusFatura());
        assertEquals(statusPagamento, fatura.getStatusPagamento());

    }

}