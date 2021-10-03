package com.impacta.microservices.fatura.controller;

import com.impacta.microservices.fatura.domain.Fatura;
import com.impacta.microservices.fatura.domain.FaturaAtualizaValorRequest;
import com.impacta.microservices.fatura.service.FaturaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class FaturaControllerTest {

    @Autowired
    private TestRestTemplate template;

    @MockBean
    private FaturaService faturaService;

    @Test
    public void criarFaturaTest(){
        final Integer contaId = 1500603805;
        final Integer clienteId = 1;
        final String mes = "janeiro";
        final String ano = "2021";
        final double valorFatura = 5.000;
        final String statusFatura = "Paga";
        final String statusPagamento = "Pagamento Integral";

        Fatura fatura = new Fatura(contaId, clienteId, mes, ano, valorFatura, statusFatura, statusPagamento);

        when(faturaService.criarFatura(fatura)).thenReturn(fatura);

        final ResponseEntity<Fatura> response = template
                .postForEntity("/fatura", fatura, Fatura.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void consultarFaturaTest(){

        final Integer contaId = 1500603805;
        final Integer clienteId = 1;
        final String mes = "janeiro";
        final String ano = "2021";
        final double valorFatura = 5.000;
        final String statusFatura = "Paga";
        final String statusPagamento = "Pagamento Integral";

        Fatura fatura = new Fatura(contaId, clienteId, mes, ano, valorFatura, statusFatura, statusPagamento);

        when(faturaService.consultaFaturaContaIdMesAno(fatura.getContaId(), fatura.getMes(), fatura.getAno())).thenReturn(java.util.Optional.of(fatura));

        final ResponseEntity<Fatura> response = template
                .getForEntity("/fatura/consulta/" + fatura.getContaId() + "/" + fatura.getMes() + "/" + fatura.getAno(), Fatura.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void pagarFaturaIntegralTest(){

        final Integer contaId = 1500603805;
        final Integer clienteId = 1;
        final String mes = "janeiro";
        final String ano = "2021";
        final double valorFatura = 5000.00;
        final String statusFatura = "Fechada";
        final String statusPagamento = "Pagamento pendente";

        final Fatura fatura = new Fatura(contaId, clienteId, mes, ano, valorFatura, statusFatura, statusPagamento);
        final FaturaAtualizaValorRequest vlrPagamentoFatura = new FaturaAtualizaValorRequest(5000.00);

        when(faturaService.pagarFaturaContaIdMesAnoValor(fatura.getContaId(), fatura.getMes(), fatura.getAno(), 5000.00)).thenReturn(fatura);

        final var request = new HttpEntity<>(vlrPagamentoFatura);

        final ResponseEntity<Fatura> response = template
                .exchange("/fatura/pagar/" + fatura.getContaId() + "/" + fatura.getMes() + "/" + fatura.getAno(), HttpMethod.PUT , request, Fatura.class);
        final Fatura result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contaId, result.getContaId());
        //assertEquals(0.0, result.getValorFatura(), 0);
        //assertEquals("Paga", result.getStatusFatura());
        //assertEquals("Pagamento Integral", result.getStatusPagamento());
    }

    @Test
    public void pagarFaturaParcialTest(){

        final Integer contaId = 1500603805;
        final Integer clienteId = 1;
        final String mes = "janeiro";
        final String ano = "2021";
        final Double valorFatura = 5.000;
        final String statusFatura = "Fechada";
        final String statusPagamento = "Pagamento pendente";

        Fatura fatura = new Fatura(contaId, clienteId, mes, ano, valorFatura, statusFatura, statusPagamento);
        FaturaAtualizaValorRequest vlrPagamentoFatura = new FaturaAtualizaValorRequest(3000.00);

        when(faturaService.pagarFaturaContaIdMesAnoValor(fatura.getContaId(), fatura.getMes(), fatura.getAno(), vlrPagamentoFatura.getValorFaturaAtualiza())).thenReturn(fatura);

        final var request = new HttpEntity<>(vlrPagamentoFatura);

        final ResponseEntity<Fatura> response = template
                .exchange("/fatura/pagar/" + fatura.getContaId() + "/" + fatura.getMes() + "/" + fatura.getAno(), HttpMethod.PUT , request, Fatura.class);
        final Fatura result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contaId, result.getContaId());
        assertEquals(java.util.Optional.of(2000.00), result.getValorFatura());
        assertEquals("Fechada - Pagamento pendente", result.getStatusFatura());
        assertEquals("Pagamento Parcial", result.getStatusPagamento());
    }

    @Test
    public void pagarFaturaCreditoTest(){

        final Integer contaId = 1500603805;
        final Integer clienteId = 1;
        final String mes = "janeiro";
        final String ano = "2021";
        final Double valorFatura = 5.000;
        final String statusFatura = "Fechada";
        final String statusPagamento = "Pagamento pendente";

        Fatura fatura = new Fatura(contaId, clienteId, mes, ano, valorFatura, statusFatura, statusPagamento);
        FaturaAtualizaValorRequest vlrPagamentoFatura = new FaturaAtualizaValorRequest(6000.00);

        when(faturaService.pagarFaturaContaIdMesAnoValor(fatura.getContaId(), fatura.getMes(), fatura.getAno(), vlrPagamentoFatura.getValorFaturaAtualiza())).thenReturn(fatura);

        final var request = new HttpEntity<>(vlrPagamentoFatura);

        final ResponseEntity<Fatura> response = template
                .exchange("/fatura/pagar/" + fatura.getContaId() + "/" + fatura.getMes() + "/" + fatura.getAno(), HttpMethod.PUT , request, Fatura.class);
        final Fatura result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contaId, result.getContaId());
        assertEquals(java.util.Optional.of(-1000.00), result.getValorFatura());
        assertEquals("Paga", result.getStatusFatura());
        assertEquals("Crédito prox.fatura", result.getStatusPagamento());
    }

}
