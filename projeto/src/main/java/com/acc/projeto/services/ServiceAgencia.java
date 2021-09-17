package com.acc.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.projeto.daos.AgenciaRepository;
import com.acc.projeto.entitys.Agencia;
import com.acc.projeto.exceptions.NotFoundException;
import com.acc.projeto.exceptions.ResourceNotFoundException;

@Service
public class ServiceAgencia {
	@Autowired
	private AgenciaRepository agenciaReposiotry;
	
	public Agencia cadastraAgencia(Agencia novaAgencia) {
		if (!(novaAgencia.getCelular() == null)) {

			if (novaAgencia.getCelular() == 0) {

				throw new ResourceNotFoundException("Número de telefone vazio");

			}
		} else {
			throw new ResourceNotFoundException("Número de telefone vazio");
		}
		if (!(novaAgencia.getNome() == null)) {

			if (novaAgencia.getNome() == "") {

				throw new ResourceNotFoundException("Nome vazio");

			}
		} else {
			throw new ResourceNotFoundException("Nome vazio");
		}
		if (!(novaAgencia.getEndereco() == null)) {

			if (novaAgencia.getEndereco() == "") {

				throw new ResourceNotFoundException("Endereço vazio");

			}
		} else {
			throw new ResourceNotFoundException("Endereço vazio");
		}
		return agenciaReposiotry.save(novaAgencia);
	}
	public List<Agencia> findAll(){
		return agenciaReposiotry.findAll();
	}
	
	public Agencia findById(Long agenciaId) {
		Optional<Agencia> optionalAgencia = agenciaReposiotry.findById(agenciaId);
		if (!optionalAgencia.isPresent()) {
			throw new NotFoundException("Agencia " + agenciaId + " nao cadastrada");
		}
		return optionalAgencia.get();
	}
	public Agencia alteraAgencia(Long agenciaId, Agencia novaAgencia) {
		Optional<Agencia> optionalAgenciaAntiga = agenciaReposiotry.findById(agenciaId);
		if (!optionalAgenciaAntiga.isPresent()) {
			throw new NotFoundException("Agencia " + agenciaId + " nao cadastrada");
		}
		if (!(novaAgencia.getCelular() == null)) {

			if (novaAgencia.getCelular() == 0) {

				throw new ResourceNotFoundException("Número de telefone vazio");

			}
		} else {
			throw new ResourceNotFoundException("Número de telefone vazio");
		}
		if (!(novaAgencia.getNome() == null)) {

			if (novaAgencia.getNome() == "") {

				throw new ResourceNotFoundException("Nome vazio");

			}
		} else {
			throw new ResourceNotFoundException("Nome vazio");
		}
		if (!(novaAgencia.getEndereco() == null)) {

			if (novaAgencia.getEndereco() == "") {

				throw new ResourceNotFoundException("Endereço vazio");

			}
		} else {
			throw new ResourceNotFoundException("Endereço vazio");
		}
		Agencia agencia = optionalAgenciaAntiga.get();
		agencia.setCelular(novaAgencia.getCelular());
		agencia.setEndereco(novaAgencia.getEndereco());
		agencia.setNome(novaAgencia.getNome());
		agenciaReposiotry.save(agencia);
		return agencia;
	}
	public void deletaAgencia(Long agenciaId) {
		Optional<Agencia> optionalAgenciaAntiga = agenciaReposiotry.findById(agenciaId);
		if (!optionalAgenciaAntiga.isPresent()) {
			throw new NotFoundException("Agencia " + agenciaId + " nao cadastrada");
		}
		agenciaReposiotry.delete(optionalAgenciaAntiga.get());
	}
}
