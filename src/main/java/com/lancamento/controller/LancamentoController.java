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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lancamento.event.RecursoCriadoEvent;
import com.lancamento.model.Lancamento;
import com.lancamento.repository.LancamentoRepository;
import com.lancamento.repository.filter.LancamentoFilter;
import com.lancamento.service.LancamentoService;

@RestController 
@RequestMapping("/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private LancamentoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
		return lancamentoRepository.filtrar(lancamentoFilter);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> findById(@PathVariable Long codigo) {
		
		Optional<Lancamento> lancamento =  repository.findById(codigo);
		
		if (lancamento.isPresent()) {
			return ResponseEntity.ok(lancamento.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> add(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = service.save(lancamento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@DeleteMapping ("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo){
		repository.deleteById(codigo);
	}
	
	
}
