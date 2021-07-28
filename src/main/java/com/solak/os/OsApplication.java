package com.solak.os;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.solak.os.domain.Cliente;
import com.solak.os.domain.OS;
import com.solak.os.domain.Tecnico;
import com.solak.os.domain.enuns.Prioridade;
import com.solak.os.domain.enuns.Status;
import com.solak.os.repositories.ClienteRepository;
import com.solak.os.repositories.OSRepository;
import com.solak.os.repositories.TecnicoRepository;

@SpringBootApplication
public class OsApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	public static void main(String[] args) {
		SpringApplication.run(OsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "142.975.760-40", "(44)98080-8888");
		Cliente c1 = new Cliente(null, "Betina Campus", "013.096.020-93", "(44)97070-8888");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
