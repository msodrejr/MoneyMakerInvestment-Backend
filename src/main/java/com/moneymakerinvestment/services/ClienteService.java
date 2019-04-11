package com.moneymakerinvestment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moneymakerinvestment.domain.Cliente;
import com.moneymakerinvestment.domain.enums.TipoCliente;
import com.moneymakerinvestment.dto.ClienteDTO;
import com.moneymakerinvestment.dto.ClienteNewDTO;
import com.moneymakerinvestment.repositories.ClienteRepository;
import com.moneymakerinvestment.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	public Cliente findOne(Integer id) {
		Optional<Cliente> obj = clienteRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepo.save(obj);
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findOne(obj.getId());
		updateData(newObj, obj);
		return clienteRepo.save(newObj);
	}

	public void delete(Integer id) {
		findOne(id);

		
		//Criar uma exception para quando houver tabelas relacionadas
		try {
			clienteRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel excluir porque há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepo.findAll();
	}
	
	public Cliente findByEmail(String email) {

		Cliente obj = clienteRepo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Cliente não encontrado! Email não cadastrado: " + email + ", Tipo: " + Cliente.class.getName());
		}

		return obj;
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));


		cli.getTelefones().add(objDto.getTelefone1());

		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}

		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}

		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
