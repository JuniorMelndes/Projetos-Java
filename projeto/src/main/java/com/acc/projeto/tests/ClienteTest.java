package com.acc.projeto.tests;

import java.util.Optional;

import com.acc.projeto.daos.ClienteRepository;
import com.acc.projeto.entitys.Cliente;
import com.acc.projeto.services.ServiceCliente;

import junit.framework.TestCase;

public class ClienteTest extends TestCase{
	private Cliente cliente;
	private ClienteRepository clienteRepository;
	private ServiceCliente serviceCliente;
	
	protected void setUp() throws Exception{
		super.setUp();
		cliente = new Cliente();
	}
	
	public void testCadastro() {
		serviceCliente.saveOrUpdate(cliente);
		Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
		assertEquals(true, optionalCliente.isPresent());
	}
	public void testConsulta() {
		serviceCliente.saveOrUpdate(cliente);
		assertEquals(true, serviceCliente.getClientebyId(cliente.getId()) == cliente);
	}
	public void testDelete() {
		serviceCliente.saveOrUpdate(cliente);
		Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
		serviceCliente.deleteCliente(cliente.getId());
		assertEquals(false, optionalCliente.isPresent());
	}
}
