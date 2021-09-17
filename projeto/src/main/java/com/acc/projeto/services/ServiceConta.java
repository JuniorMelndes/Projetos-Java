package com.acc.projeto.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.projeto.daos.AgenciaRepository;
import com.acc.projeto.daos.ClienteRepository;
import com.acc.projeto.daos.ContaRepository;
import com.acc.projeto.daos.ExtratoRepository;
import com.acc.projeto.entitys.Agencia;
import com.acc.projeto.entitys.Cliente;
import com.acc.projeto.entitys.Conta;
import com.acc.projeto.entitys.Extrato;
import com.acc.projeto.enums.Operacao;
import com.acc.projeto.exceptions.NotFoundException;
import com.acc.projeto.exceptions.ResourceNotFoundException;

@Service
public class ServiceConta {
	
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private AgenciaRepository agenciaRepository;
	@Autowired
	private ExtratoRepository extratoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Conta> getAll(){
		return contaRepository.findAll();
	}
	
	public Conta findById(Long id) {
		Optional<Conta> optionalconta = contaRepository.findById(id);
		if (!optionalconta.isPresent()) {
			throw new NotFoundException("Conta " + id + " nao cadastrada");
		}
		return optionalconta.get();
	}
	
	public Conta cadastraConta(Conta conta, Long agenciaId, Long clienteId ) {
		if (!(conta.getNumeroConta() == null)) {

			if (conta.getNumeroConta() == 0) {

				throw new ResourceNotFoundException("Número de conta vazio");

			}
		} else {
			throw new ResourceNotFoundException("Número de conta vazio");
		}
		Optional<Agencia> agencia = agenciaRepository.findById(agenciaId);
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		if (!agencia.isPresent()) {
			throw new NotFoundException("Agencia " + agenciaId + " nao cadastrada");
		}
		if (!cliente.isPresent()) {
			throw new NotFoundException("Cliente " + clienteId + " nao cadastrado");
		}
		conta.setCliente(cliente.get());
		conta.setAgencia(agencia.get());
		return contaRepository.save(conta);
	}
	
	public void deletaConta(Long id) {
		Optional<Conta> optionalconta = contaRepository.findById(id);
		if (!optionalconta.isPresent()) {
			throw new NotFoundException("Conta " + id + " nao cadastrada");
		}
		contaRepository.delete(optionalconta.get());
	}
	
	public Conta alteraConta(Long id, Conta novaconta) {
		Optional<Conta> contaAntiga = contaRepository.findById(id);
		if (!contaAntiga.isPresent()) {
			throw new NotFoundException("Conta " + id + " nao cadastrada");
		}
		if (!(novaconta.getNumeroConta() == null)) {

			if (novaconta.getNumeroConta() == 0) {

				throw new ResourceNotFoundException("Número de conta vazio");

			}
		} else {
			throw new ResourceNotFoundException("Número de conta vazio");
		}
		Conta conta = contaAntiga.get();
		conta.setNumeroConta(novaconta.getNumeroConta());
		contaRepository.save(conta);
		return conta;
	}
	public Extrato depositoConta(long idconta, Extrato novoExtrato) {
		Optional<Conta> optionalconta = contaRepository.findById(idconta);
		if (novoExtrato.getValorDaOperacao() < 0) {
			throw new NotFoundException("Valor da operação não pode ser negativo");
		}
		if (!optionalconta.isPresent()) {
			throw new NotFoundException("Conta " + idconta + " nao cadastrada");
		}
		Extrato extrato = new Extrato();
		extrato.setOpercao(Operacao.DEPOSITO);
		extrato.setValorDaOperacao(novoExtrato.getValorDaOperacao());
		ZoneId zone = ZoneId.of("America/Sao_Paulo");
		LocalDateTime localDate = LocalDateTime.now(zone);
		extrato.setDataMovimentacao(localDate);
		Double saldo_atual = optionalconta.get().getSaldo();
		optionalconta.get().setSaldo(saldo_atual + extrato.getValorDaOperacao());
		extrato.setSaldoResultante(optionalconta.get().getSaldo());
		extrato.setConta(optionalconta.get());
		return extratoRepository.save(extrato);
		
	}
	public Extrato saqueConta(long idconta, Extrato novoExtrato) {
		Optional<Conta> optionalconta = contaRepository.findById(idconta);
		if (novoExtrato.getValorDaOperacao() < 0) {
			throw new NotFoundException("Valor da operação não pode ser negativo");
		}
		if (!optionalconta.isPresent()) {
			throw new NotFoundException("Conta " + idconta + " nao cadastrada");
		}
		Double saldo_atual = optionalconta.get().getSaldo();
		if (saldo_atual - novoExtrato.getValorDaOperacao() < 0) {
			throw new NotFoundException("Saldo Insuficiente");
		}
		Extrato extrato = new Extrato();
		extrato.setOpercao(Operacao.SAQUE);
		extrato.setValorDaOperacao(novoExtrato.getValorDaOperacao());
		ZoneId zone = ZoneId.of("America/Sao_Paulo");
		LocalDateTime localDate = LocalDateTime.now(zone);
		extrato.setDataMovimentacao(localDate);
		optionalconta.get().setSaldo(saldo_atual - extrato.getValorDaOperacao());
		extrato.setSaldoResultante(optionalconta.get().getSaldo());
		extrato.setConta(optionalconta.get());
		return extratoRepository.save(extrato);
		
	}
	
	public Extrato transferenciaEntreContas(long idContaOrigem, Extrato novoExtrato) {
		Long idContaDestino = novoExtrato.getIdContaDestino();
		if (novoExtrato.getValorDaOperacao() < 0) {
			throw new NotFoundException("Valor da operação não pode ser negativo");
		}
		Optional<Conta> contaDestino = contaRepository.findById(idContaDestino);
		Optional<Conta> contaOrigem = contaRepository.findById(idContaOrigem);
		if (!contaOrigem.isPresent()) {
			throw new NotFoundException("Conta " + idContaOrigem + " nao cadastrada");
		}
		if (!contaDestino.isPresent()) {
			throw new NotFoundException("Conta " + idContaDestino + " nao cadastrada");
		}
		Double saldoAtualOrigem = contaOrigem.get().getSaldo();
		Double saldoAtualDestino = contaDestino.get().getSaldo();
		if (saldoAtualOrigem - novoExtrato.getValorDaOperacao() < 0) {
			throw new NotFoundException("Saldo Insuficiente");
		}
		Extrato extratoDebito = new Extrato();
		extratoDebito.setOpercao(Operacao.TRANSFERENCIA_DEBITO);
		extratoDebito.setValorDaOperacao(novoExtrato.getValorDaOperacao());
		ZoneId zone = ZoneId.of("America/Sao_Paulo");
		LocalDateTime localDate = LocalDateTime.now(zone);
		extratoDebito.setDataMovimentacao(localDate);
		extratoDebito.setConta(contaOrigem.get());
		extratoDebito.setIdContaDestino(idContaDestino);
		Extrato extratoCredito = new Extrato();
		extratoCredito.setOpercao(Operacao.TRANSFERENCIA_CREDITO);
		extratoCredito.setValorDaOperacao(novoExtrato.getValorDaOperacao());
		extratoCredito.setDataMovimentacao(localDate);
		extratoCredito.setConta(contaDestino.get());
		contaOrigem.get().setSaldo(saldoAtualOrigem - extratoDebito.getValorDaOperacao());
		contaDestino.get().setSaldo(saldoAtualDestino + extratoDebito.getValorDaOperacao());
		extratoCredito.setSaldoResultante(contaDestino.get().getSaldo());
		extratoDebito.setSaldoResultante(contaOrigem.get().getSaldo());
		extratoRepository.save(extratoCredito);
		return extratoRepository.save(extratoDebito);
	}
	public List<Extrato> getExtrato(long id){
		Optional<Conta> optionalConta = contaRepository.findById(id);
		if (!optionalConta.isPresent()) {
			throw new NotFoundException("Conta " + id + " nao cadastrada");
		}
		List<Extrato> extratos = extratoRepository.findAll();
		List<Extrato> extratosPorId = new ArrayList<>();
		for (int i = 0; i < extratos.size(); i++) {
			if(extratos.get(i).getConta().getId() == id) {
				extratosPorId.add(extratos.get(i));
			}
		}
		return extratosPorId;
	}
}
