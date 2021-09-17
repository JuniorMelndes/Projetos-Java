package com.acc.projeto.tests;

import java.util.Optional;

import com.acc.projeto.daos.AgenciaRepository;
import com.acc.projeto.entitys.Agencia;
import com.acc.projeto.services.ServiceAgencia;

import junit.framework.TestCase;

public class AgenciaTest extends TestCase {
	private Agencia agencia;
	private AgenciaRepository agenciaRepository;
	private ServiceAgencia serviceAgencia;
	
	protected void setUp() throws Exception{
		super.setUp();
		agencia = new Agencia();
	}
	public void testCadastro() {
		serviceAgencia.cadastraAgencia(agencia);
		Optional<Agencia> optionalAgencia = agenciaRepository.findById(agencia.getId());
		assertEquals(true, optionalAgencia.isPresent());
	}
	public void testConsulta() {
		serviceAgencia.cadastraAgencia(agencia);
		assertEquals(true, serviceAgencia.findById(agencia.getId()) == agencia);
	}
	public void testDelete() {
		serviceAgencia.cadastraAgencia(agencia);
		Optional<Agencia> optionalAgencia = agenciaRepository.findById(agencia.getId());
		serviceAgencia.deletaAgencia(agencia.getId());
		assertEquals(false, optionalAgencia.isPresent());
	}
}

