package com.impacta.microservices.fatura.service;

import com.impacta.microservices.fatura.client.ContaCorrenteClient;
import com.impacta.microservices.fatura.client.DebitoClient;
import com.impacta.microservices.fatura.client.response.ConsultaContaCorrente;
import com.impacta.microservices.fatura.client.response.CriarDebito;
import com.impacta.microservices.fatura.domain.Fatura;
import com.impacta.microservices.fatura.repository.FaturaRepository;
import org.springframework.stereotype.Component;

@Component
public class FaturaService {

    private FaturaRepository repository;
    private ContaCorrenteClient contaCorrenteClient;
    private DebitoClient debitoClient;

    public FaturaService(FaturaRepository repository,
                         ContaCorrenteClient contaCorrenteClient,
                         DebitoClient debitoClient) {
        this.repository = repository;
        this.contaCorrenteClient = contaCorrenteClient;
        this.debitoClient = debitoClient;
    }

    public Fatura criarFatura(Fatura fatura){
        return repository.save(fatura);
    }

    public Fatura consultaFaturaContaIdMesAno(Integer contaId, String mes, String ano) {

        return  repository.findByContaIdAndMesAndAno(contaId, mes, ano);

    }

    public Fatura pagarFaturaContaIdMesAnoValor(Integer contaId, String mes, String ano, Double valorPagar) {

        ConsultaContaCorrente consultaContaCorrente = contaCorrenteClient.getDadosContaCorrente(contaId);
        Double valorAtual = repository.findByValorFatura(contaId, mes, ano);

        var fatura = consultaFaturaContaIdMesAno(contaId, mes, ano);

        if (consultaContaCorrente.getSaldo() >= valorAtual) {

            Double valorDif = valorAtual - valorPagar;
            fatura.setValorFatura(valorDif);

            if(valorDif == 0){
                fatura.setStatusFatura("Paga");
                fatura.setStatusPagamento("Pagamento Integral");
            }else if(valorDif < 0){
                fatura.setStatusFatura("Paga");
                fatura.setStatusPagamento("Crédito prox.fatura");
            }else if (valorDif > 0){
                fatura.setStatusFatura("Fechada - Pagamento pendente");
                fatura.setStatusPagamento("Pagamento Parcial");
            }

            CriarDebito criarDebito = new CriarDebito(contaId, valorPagar, consultaContaCorrente.getClienteId(), "contacorrente");
            debitoClient.criarDebito(criarDebito);

        }else{
            fatura.setStatusPagamento("Saldo insuficiente em conta corrente - Transação negada");
        }

        return criarFatura(fatura);

    }

}
