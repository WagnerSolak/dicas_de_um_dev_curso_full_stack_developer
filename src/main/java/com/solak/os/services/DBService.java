package com.solak.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solak.os.domain.Cliente;
import com.solak.os.domain.OS;
import com.solak.os.domain.Tecnico;
import com.solak.os.domain.enuns.Prioridade;
import com.solak.os.domain.enuns.Status;
import com.solak.os.repositories.ClienteRepository;
import com.solak.os.repositories.OSRepository;
import com.solak.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;
	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "142.975.760-40", "(44)98080-8888");
		Tecnico t2 = new Tecnico(null, "Wagner Solak", "945.908.260-20", "(44)95555-7777");
		Cliente c1 = new Cliente(null, "Betina Campus", "013.096.020-93", "(44)97070-8888");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1,t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
