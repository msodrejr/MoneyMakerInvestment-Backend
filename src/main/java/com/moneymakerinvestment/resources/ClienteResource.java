package com.moneymakerinvestment.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moneymakerinvestment.domain.Cliente;
import com.moneymakerinvestment.domain.enums.TipoCliente;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> listar() {
		Cliente cli = new Cliente(1, "Mauricio Junior", "mss.junior@globo.com", "123456789", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(2, "Rolando Caio da Rocha", "RolandoCaio@globo.com", "123459876", TipoCliente.PESSOAFISICA);
		List<Cliente> lista = new ArrayList<>();
		lista.add(cli);
		lista.add(cli2);
		return lista;
	}

}
