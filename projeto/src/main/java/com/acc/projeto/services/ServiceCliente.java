package com.acc.projeto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.projeto.daos.ClienteRepository;
import com.acc.projeto.entitys.Cliente;
import com.acc.projeto.exceptions.NotFoundException;
import com.acc.projeto.exceptions.ResourceNotFoundException;



@Service
public class ServiceCliente {

	@Autowired
	ClienteRepository clienteRepository;

	public List<Cliente> getAllClientes() {

		List<Cliente> clientes = new ArrayList<Cliente>();
		clienteRepository.findAll().forEach(cliente -> clientes.add(cliente));
		return clientes;
	}

	public Cliente getClientebyId(long id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (!optionalCliente.isPresent()) {
			throw new NotFoundException("Cliente " + id + " não cadastrado");
		}
		return optionalCliente.get();

	}

	public Cliente saveOrUpdate(Cliente cliente) {

		if (!(cliente.getNome() == null)) {

			if (cliente.getNome() == "") {

				throw new ResourceNotFoundException("Nome vazio");

			}
		} else {
			throw new ResourceNotFoundException("Nome vazio");
		}

		if (!((cliente.getCpf()) == null)) {

			if (!(cliente.getCpf().length() == 14)) {

				throw new ResourceNotFoundException("CPF inválido");

			}

		} else {
			throw new ResourceNotFoundException("CPF vazio");
		}

		if (!(cliente.getFone() == null)) {

			if (cliente.getFone() == "") {

				throw new ResourceNotFoundException("Fone vazio");

			}

		} else {
			throw new ResourceNotFoundException("Fone vazio");
		}

		return clienteRepository.save(cliente);
	}

	public void deleteCliente(long id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (!optionalCliente.isPresent()) {
			throw new NotFoundException("Cliente " + id + " não cadastrado");
		}
		clienteRepository.delete(optionalCliente.get());

	}

	public Cliente alterarCliente(Long id, Cliente novoCliente) {
		Optional<Cliente> ClienteAntigo = clienteRepository.findById(id);
		if (!ClienteAntigo.isPresent()) {
			throw new NotFoundException("Cliente " + id + " não cadastrado");
		}
		if (!(novoCliente.getNome() == null)) {

			if (novoCliente.getNome() == "") {

				throw new ResourceNotFoundException("Nome vazio");

			}
		} else {
			throw new ResourceNotFoundException("Nome vazio");
		}

		if (!((novoCliente.getCpf()) == null)) {

			if (!(novoCliente.getCpf().length() == 14)) {

				throw new ResourceNotFoundException("CPF inválido");

			}

		} else {
			throw new ResourceNotFoundException("CPF vazio");
		}

		if (!(novoCliente.getFone() == null)) {

			if (novoCliente.getFone() == "") {

				throw new ResourceNotFoundException("Fone vazio");

			}

		} else {
			throw new ResourceNotFoundException("Fone vazio");
		}
		Cliente cliente = ClienteAntigo.get();
		cliente.setCpf(novoCliente.getCpf());
		cliente.setFone(novoCliente.getFone());
		cliente.setNome(novoCliente.getNome());
		clienteRepository.save(cliente);
		return cliente;
	}

}
