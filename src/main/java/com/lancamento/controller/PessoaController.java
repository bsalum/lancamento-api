package com.lancamento.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lancamento.model.Pessoa;
import com.lancamento.repository.PessoaRepository;
import com.lancamento.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private PessoaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Pessoa> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long codigo) {
		
		Optional<Pessoa> pessoa =  repository.findById(codigo);
		
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> add(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = service.save(pessoa);
		
		publisher.publishEvent(new com.lancamento.event.RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
			
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long codigo ) {
		service.deleteById(codigo);
	}
	
	
	@PutMapping ("/{codigo}") 
	public ResponseEntity<Pessoa> update(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
		
		if (!repository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		
		pessoa.setCodigo(codigo); 
		pessoa = service.save(pessoa);
		return ResponseEntity.ok(pessoa);
	}
	
	@PutMapping("/{codigo}/ativo") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		service.updatePropriedadeAtivo(codigo, ativo);
	}

}
