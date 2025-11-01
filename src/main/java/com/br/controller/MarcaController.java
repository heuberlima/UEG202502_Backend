package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Marca;
import com.br.repository.MarcaRepository;


@CrossOrigin(origins = "*")
@RequestMapping("/cmarca/")
@RestController
public class MarcaController {

	//Cria o repositorio JPA para ser usado pelo controlador
	@Autowired
	private MarcaRepository mRep;
	
	//Listar
	@GetMapping("/marca")
	public List<Marca> listar(){
		return this.mRep.findAll();
	}

	//Consultar
	@GetMapping("/marca/{id}")
	public ResponseEntity<Marca> consultar(@PathVariable Long id){
		
		Marca marca = this.mRep.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Marca não encontrada: " + id));
		
		return ResponseEntity.ok(marca);
	}
	
	
	//Inserir
	@PostMapping("/marca")
	public Marca inserir(@RequestBody Marca marca) {
		
		return this.mRep.save(marca);
		
	}
	
	//Alterar
	@PutMapping("/marca/{id}")
	public ResponseEntity<Marca> alterar(@PathVariable Long id, @RequestBody Marca marca){
		
		Marca marcLoc = this.mRep.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Marca não encontrada: " + id));
		
		marcLoc.setCodigo(marca.getCodigo());
		marcLoc.setNome(marca.getNome());

		
		Marca atualizado = this.mRep.save(marcLoc);
		
		return ResponseEntity.ok(atualizado);
		
		
	}
	
	
	//Excluir
	@DeleteMapping("/marca/{id}")
	public ResponseEntity<Map<String,Boolean>> excluir(@PathVariable Long id){
		
		Marca marca = this.mRep.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Marca não encontrada: " + id));
		
		mRep.delete(marca);
		
		Map<String,Boolean> resposta = new HashMap<>();
		resposta.put("Marca excluída!", Boolean.TRUE);
		return ResponseEntity.ok(resposta);
		
		
	}
}
