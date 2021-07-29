package com.solak.os.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solak.os.domain.Tecnico;
import com.solak.os.dtos.TecnicoDTO;
import com.solak.os.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos") // endpoint inicial para acessar os recursos do Tecnico
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	// para acessar: localhost:8080/tecnicos
	
	@GetMapping(value = "/{id}") // {id} variável de path
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
	//  Tecnico obj = service.findById(id);
	//	TecnicoDTO objDTO = new TecnicoDTO(obj); // conversao do Tecnico em TecnicoDTO (boas práticas)
		TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
	
	// na criação inicial estávamos retornando a entidade <Tecnico>:  
	// @GetMapping(value = "/{id}") // {id} variável de path
	// public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
	//		Tecnico obj = service.findById(id);
	//		return ResponseEntity.ok().body(obj);
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		
		List<TecnicoDTO> listDTO = service.findAll()
				.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		/*
		 *
		 * List<Tecnico> list = service.findAll(); List<TecnicoDTO> listDTO = new ArrayList<>();
		 * 
		 * for(Tecnico obj : list) { listDTO.add(new TecnicoDTO(obj)); }
		 * list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
		 */
		
		return ResponseEntity.ok().body(listDTO);
	}
}
