package com.impacta.microservices.fatura.service;

import com.impacta.microservices.fatura.domain.Fatura;
import com.impacta.microservices.fatura.repository.FaturaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class FaturaService {

    private FaturaRepository repository;

    public FaturaService(FaturaRepository repository) {
        this.repository = repository;
    }

    public Fatura criarFatura(Fatura fatura){
        return repository.save(fatura);
    }

    public Fatura consultaFaturaContaIdMesAno(Integer contaId, String mes, String ano) {

        return  repository.findByContaIdAndMesAndAno(contaId, mes, ano);

    }

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

}
