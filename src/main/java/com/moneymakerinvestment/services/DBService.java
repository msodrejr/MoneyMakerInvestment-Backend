package com.moneymakerinvestment.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymakerinvestment.domain.Cliente;
import com.moneymakerinvestment.domain.enums.TipoCliente;
import com.moneymakerinvestment.repositories.ClienteRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public void instantiateTestDatabase() throws ParseException {
		
		//Instanciando os clientes
		
		Cliente cli1 = new Cliente(null, "Maurício Sodré da Silva Junior", "m.sodre8@gmail.com",
				"64217507704", TipoCliente.PESSOAFISICA);
		
		Cliente cli2 = new Cliente(null, "Rolando Caio da Rocha", "rolandinho@gmail.com",
				"23655108761", TipoCliente.PESSOAFISICA);
		
		Cliente cli3 = new Cliente(null, "Rolando Lero da Silva", "rolandolero123@gmail.com",
				"47976861700", TipoCliente.PESSOAFISICA);
		

		//Persistindo os clientes
		clienteRepo.saveAll(Arrays.asList(cli1, cli2, cli3));
	}

}
