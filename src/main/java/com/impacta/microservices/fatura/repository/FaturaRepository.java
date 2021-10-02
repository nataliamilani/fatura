package com.impacta.microservices.fatura.repository;

import com.impacta.microservices.fatura.domain.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

    Fatura findByContaIdAndMesAndAno(Integer contaId, String mes, String ano);

    List<Fatura> findByContaId(Integer contaId);

    @Query(value = "select valor_fatura from fatura where conta_id = ?1 and mes = ?2 and ano = ?3", nativeQuery = true)
    Double findByValorFatura(Integer contaId, String mes, String ano);

}
