package com.acc.projeto.tests;

import java.util.Optional;

import com.acc.projeto.daos.ContaRepository;
import com.acc.projeto.entitys.Agencia;
import com.acc.projeto.entitys.Cliente;
import com.acc.projeto.entitys.Conta;
import com.acc.projeto.services.ServiceConta;

import junit.framework.TestCase;

public class ContaTest extends TestCase {
	private Conta conta;
	private Agencia agencia;
	private Cliente cliente;
	private ContaRepository contaRepository;
	private ServiceConta serviceConta;
	
	protected void setUp() throws Exception{
		super.setUp();
		conta = new Conta();
		agencia = new Agencia();
		cliente = new Cliente();
	}
	
	public void testCadastro() {
		serviceConta.cadastraConta(conta, agencia.getId(), cliente.getId());
		Optional<Conta> optionalConta = contaRepository.findById(conta.getId());
		assertEquals(true, optionalConta.isPresent());
	}
	public void testConsulta() {
		serviceConta.cadastraConta(conta, agencia.getId(), cliente.getId());
		assertEquals(true, serviceConta.findById(conta.getId()) == conta);
	}
	public void testDelete() {
		serviceConta.cadastraConta(conta, agencia.getId(), cliente.getId());
		Optional<Conta> optionalConta = contaRepository.findById(conta.getId());
		serviceConta.deletaConta(conta.getId());
		assertEquals(false, optionalConta.isPresent());
	}
}
