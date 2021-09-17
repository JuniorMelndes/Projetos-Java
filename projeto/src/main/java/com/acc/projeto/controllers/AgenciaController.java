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

import com.acc.projeto.entitys.Agencia;
import com.acc.projeto.services.ServiceAgencia;

@RestController
public class AgenciaController {
	
	@Autowired
	private ServiceAgencia serviceAgencia;
	
	@RequestMapping(value = "/agencia", method = RequestMethod.POST)
	public ResponseEntity<Agencia> criaAgencia(@Valid @RequestBody Agencia novaAgencia){
		return new ResponseEntity<>(serviceAgencia.cadastraAgencia(novaAgencia), HttpStatus.CREATED);
	}
	@RequestMapping(value = "/agencia", method = RequestMethod.GET)
	public ResponseEntity<List<Agencia>> listaAgencias(){
		return new ResponseEntity<>(serviceAgencia.findAll(), HttpStatus.OK);
	}
	@RequestMapping(value = "/agencia/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletaAgencia(@PathVariable(value = "id") long id){
		serviceAgencia.deletaAgencia(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value = "/agencia/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Agencia> alteraAgencia(@PathVariable(value = "id") long id, @Valid @RequestBody Agencia novaAgencia){
		return new ResponseEntity<>(serviceAgencia.alteraAgencia(id, novaAgencia), HttpStatus.OK);
	}
	@RequestMapping(value = "/agencia/{id}", method = RequestMethod.GET)
	public ResponseEntity<Agencia> findById(@PathVariable(value = "id") long id){
		return new ResponseEntity<>(serviceAgencia.findById(id), HttpStatus.OK);
	}
}
