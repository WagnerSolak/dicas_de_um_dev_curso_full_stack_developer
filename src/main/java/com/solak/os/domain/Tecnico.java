package com.solak.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore // para ignorar a lista quando buscarmos por get (para nao entrar no loop infinito)
	@OneToMany(mappedBy = "tecnico")
	private List<OS> list = new ArrayList<>();
	
	// para instanciar os tecnicos com mais facilidade, é
	// necessário o construtor da superclasse
	public Tecnico() {
		super();
	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	
	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}
	
	

}
