package com.solak.os.domain;

public class Tecnico extends Pessoa {

	// para instanciar os tecnicos com mais facilidade, é
	// necessário o construtor da superclasse
	public Tecnico() {
		super();
	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

}
