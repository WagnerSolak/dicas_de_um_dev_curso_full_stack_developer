package com.solak.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solak.os.domain.Pessoa;
import com.solak.os.domain.Tecnico;
import com.solak.os.dtos.TecnicoDTO;
import com.solak.os.repositories.PessoaRepository;
import com.solak.os.repositories.TecnicoRepository;
import com.solak.os.services.exceptions.DataIntegratyViolationException;
import com.solak.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); // buscou e verificou se existe ou nao atraves do Optional
		// return obj.orElse(null);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		//Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		//return repository.save(newObj);
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	// private Tecnico findByCPF(TecnicoDTO objDTO) {
	// alterado Tecnico por Pessoa pois na regra atual ele deixaria criar um cf para um tecnico ou cliente
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		
		if(obj != null) {
			return obj;
		}
		return null;
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);
		
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		
		
		return repository.save(oldObj);
		
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getList().size() > 0) {  // se for > 0 Tecnico têm os vinculado
			throw new DataIntegratyViolationException("Técnico possui Ordens de serviço, não podendo ser deletado!");
		}
		repository.deleteById(id);
	}
}
