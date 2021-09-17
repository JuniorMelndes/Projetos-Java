package com.acc.projeto.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acc.projeto.entitys.*;
import com.acc.projeto.services.ServiceConta;


@RestController
public class ContaController {
	
	@Autowired
	private ServiceConta serviceConta;
	
	@RequestMapping(value = "/conta", method = RequestMethod.GET)
	public ResponseEntity<List<Conta>> getAll(){
		return new ResponseEntity<>(serviceConta.getAll(), HttpStatus.OK);
	}
	@RequestMapping(value = "/conta/{agenciaId}/{clienteId}", method = RequestMethod.POST)
	public ResponseEntity<Conta> criaConta(@PathVariable (value = "agenciaId") Long agenciaId, @PathVariable (value = "clienteId") Long clienteId, @Valid @RequestBody Conta novaConta){
		return new ResponseEntity<>(serviceConta.cadastraConta(novaConta, agenciaId, clienteId), HttpStatus.CREATED);
	}
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletaConta(@PathVariable(value = "id") long id){
		serviceConta.deletaConta(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Conta> alteraConta(@PathVariable(value = "id") long id, @Valid @RequestBody Conta novaConta){
		return new ResponseEntity<>(serviceConta.alteraConta(id, novaConta), HttpStatus.OK);
	}
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.GET)
	public ResponseEntity<Conta> getById(@PathVariable(value = "id") long id){
		return new ResponseEntity<>(serviceConta.findById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/conta/{id}/deposito", method = RequestMethod.POST)
	public ResponseEntity<Extrato> realizaDeposito(@PathVariable(value = "id") Long id, @Valid @RequestBody Extrato extrato){
		return new ResponseEntity<>(serviceConta.depositoConta(id, extrato), HttpStatus.CREATED);
	}
	@RequestMapping(value = "/conta/{id}/saque", method = RequestMethod.POST)
	public ResponseEntity<Extrato> realizaSaque(@PathVariable(value = "id") Long id, @Valid @RequestBody Extrato extrato){
		return new ResponseEntity<>(serviceConta.saqueConta(id, extrato), HttpStatus.CREATED);
	}
	@RequestMapping(value = "/conta/{id}/transferencia", method = RequestMethod.POST)
	public ResponseEntity<Extrato> realizaTransferencia(@PathVariable(value = "id") Long id, @Valid @RequestBody Extrato extrato){
		return new ResponseEntity<>(serviceConta.transferenciaEntreContas(id, extrato), HttpStatus.CREATED);
	}
	@RequestMapping(value = "/conta/{id}/extrato", method = RequestMethod.GET)
	public ResponseEntity<List<Extrato>> consultaExtratos(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(serviceConta.getExtrato(id), HttpStatus.OK);
	}
}
