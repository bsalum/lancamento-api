package com.lancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancamento.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
