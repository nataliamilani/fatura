package com.impacta.microservices.fatura.service;

import com.impacta.microservices.fatura.client.ContaCorrenteClient;
import com.impacta.microservices.fatura.client.DebitoClient;
import com.impacta.microservices.fatura.domain.Fatura;
import com.impacta.microservices.fatura.domain.FaturaAtualizaValorRequest;
import com.impacta.microservices.fatura.exceptions.FaturaNotFoundException;
import com.impacta.microservices.fatura.repository.FaturaRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class FaturaServiceTest {


    @InjectMocks
    private FaturaService faturaService;

    @Mock
    private FaturaRepository faturaRepository;

    @Mock
    private ContaCorrenteClient contaCorrenteClient;

    @Mock
    private DebitoClient debitoClient;

    @Before
    public void setUp() {
        faturaRepository.deleteAll();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void salvarFaturaCriada(){

        final Integer contaId = 1500603805;
        final Integer clienteId = 1;
        final String mes = "janeiro";
        final String ano = "2021";
        final double valorFatura = 5000.00;
        final String statusFatura = "Fechada";
        final String statusPagamento = "Pagamento pendente";

        Fatura fatura = new Fatura(contaId, clienteId, mes, ano, valorFatura, statusFatura, statusPagamento);
        FaturaAtualizaValorRequest vlrPagamentoFatura = new FaturaAtualizaValorRequest(5000.00);


        when(faturaRepository.save(fatura)).thenReturn(fatura);

        final Fatura result = faturaService.criarFatura(fatura);

        assertEquals(contaId, result.getContaId());
        assertEquals(clienteId, result.getClienteId());
        assertEquals(statusPagamento, result.getStatusPagamento());
    }


    @Test(expected = FaturaNotFoundException.class)
    public void faturaInexistenteNotFoundTest(){
        when(faturaService.consultaFaturaContaIdMesAno(123456789, "janeiro", "2024")).thenThrow(FaturaNotFoundException.class);
    }


    @Test(expected = FaturaNotFoundException.class)
    public void pagarFaturaInexistenteNotFoundTest(){
        when(faturaService.pagarFaturaContaIdMesAnoValor(123456789, "janeiro", "2024", 5000.00)).thenThrow(FaturaNotFoundException.class);
    }


}
