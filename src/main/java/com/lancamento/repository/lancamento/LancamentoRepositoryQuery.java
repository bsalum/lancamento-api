package com.lancamento.repository.lancamento;

import java.util.List;

import com.lancamento.model.Lancamento;
import com.lancamento.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
