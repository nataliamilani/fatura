package com.impacta.microservices.fatura.service;

import com.impacta.microservices.fatura.client.ContaCorrenteClient;
import com.impacta.microservices.fatura.client.response.SaldoContaCorrente;
import com.impacta.microservices.fatura.domain.Fatura;
import com.impacta.microservices.fatura.repository.FaturaRepository;
import org.springframework.stereotype.Component;

@Component
public class FaturaService {

    private FaturaRepository repository;
    private ContaCorrenteClient contaCorrenteClient;

    public FaturaService(FaturaRepository repository,
                         ContaCorrenteClient contaCorrenteClient) {
        this.repository = repository;
        this.contaCorrenteClient = contaCorrenteClient;
    }

    public Fatura criarFatura(Fatura fatura){
        return repository.save(fatura);
    }

    public Fatura consultaFaturaContaIdMesAno(Integer contaId, String mes, String ano) {

        return  repository.findByContaIdAndMesAndAno(contaId, mes, ano);

    }
/*
    public Fatura pagarFaturaContaIdMesAnoValor(Integer contaId, String mes, String ano, Double valorPagar) {

        Double valorAtual = repository.findByValorFatura(contaId, mes, ano);
        Double valorDif = valorAtual - valorPagar;
        var fatura = consultaFaturaContaIdMesAno(contaId, mes, ano);
        fatura.setValorFatura(valorDif);

        if(valorDif == 0){
            fatura.setStatusFatura("Pagamento Integral");
        }else if(valorDif < 0){
            fatura.setStatusFatura("Crédito prox.fatura");
        }else if (valorDif > 0){
            fatura.setStatusFatura("Pagamento Parcial");
        }

        return criarFatura(fatura);

    }

 */

    public Fatura pagarFaturaContaIdMesAnoValor(Integer contaId, String mes, String ano, Double valorPagar) {

        SaldoContaCorrente saldoContaCorrente = contaCorrenteClient.getSaldoContaCorrente(contaId);
        Double valorAtual = repository.findByValorFatura(contaId, mes, ano);

        var fatura = consultaFaturaContaIdMesAno(contaId, mes, ano);

        if (saldoContaCorrente.getValorContaCorrente() >= valorAtual) {

            Double valorDif = valorAtual - valorPagar;
            fatura.setValorFatura(valorDif);

            if(valorDif == 0){
                fatura.setStatusFatura("Pagamento Integral");
            }else if(valorDif < 0){
                fatura.setStatusFatura("Crédito prox.fatura");
            }else if (valorDif > 0){
                fatura.setStatusFatura("Pagamento Parcial");
            }

        }else{
            fatura.setStatusFatura("Saldo insuficiente em conta corrente bloco- Transação negada");
        }

        return criarFatura(fatura);

    }

    public SaldoContaCorrente consultaSaldoContaCorrente(Integer contaId){
        SaldoContaCorrente saldoContaCorrente = contaCorrenteClient.getSaldoContaCorrente(contaId);
        return saldoContaCorrente;
    }

}
