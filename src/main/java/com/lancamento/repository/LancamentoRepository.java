package com.lancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancamento.model.Lancamento;
import com.lancamento.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
