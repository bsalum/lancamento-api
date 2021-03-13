package com.lancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancamento.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
