package com.solak.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solak.os.domain.Cliente;
import com.solak.os.domain.OS;
import com.solak.os.domain.Tecnico;
import com.solak.os.domain.enuns.Prioridade;
import com.solak.os.domain.enuns.Status;
import com.solak.os.dtos.OSDTO;
import com.solak.os.repositories.OSRepository;
import com.solak.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj); // fromDTO recebe um Obj de Transf. e instancia uma nova OS 
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		// forma de buscar o id para passar para o obj, e tb " é uma forma" de validação, pois lança excecao se nulo
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		return repository.save(newObj);
	}

	
}
