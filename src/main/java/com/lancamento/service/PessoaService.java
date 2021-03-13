package com.lancamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lancamento.model.Pessoa;
import com.lancamento.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository repository;

	public Pessoa save(Pessoa pessoa) {
		return repository.save(pessoa);
	}
	
	public void deleteById(Long codigo) {
		repository.deleteById(codigo);
	}
		
	public void updatePropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = findById(codigo);
		pessoaSalva.setAtivo(ativo);
		repository.save(pessoaSalva);
	}
	
	public Pessoa findById(Long codigo) {
		Pessoa pessoaSalva = repository.findById(codigo).orElse(null);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
