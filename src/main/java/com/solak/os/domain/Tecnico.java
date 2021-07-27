package com.solak.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
