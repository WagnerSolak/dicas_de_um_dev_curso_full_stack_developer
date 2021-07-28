package com.solak.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.solak.os.services.DBService;

@Configuration
@Profile("test")
public class TesteConfig {

	@Autowired
	private DBService dbService;

	@Bean // quando profile test estiver atio, ele verifica o método Bean e executa o metodo, que nele possui uma instancia do dbService que instanciará os objetos da classe chamada DBService
	public void instanciarDB() {
		this.dbService.instanciaDB();
	}

}
