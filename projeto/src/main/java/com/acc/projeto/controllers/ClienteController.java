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

import com.acc.projeto.entitys.Cliente;
import com.acc.projeto.services.ServiceCliente;


@RestController
public class ClienteController {

	@Autowired
	ServiceCliente serviceCliente;

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAll() {
		return new ResponseEntity<>(serviceCliente.getAllClientes(), HttpStatus.OK);

	}

	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente novoCliente) {
		return new ResponseEntity<>(serviceCliente.saveOrUpdate(novoCliente), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletaContaCorrente(@PathVariable(value = "id") long id) {
		serviceCliente.deleteCliente(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> alteraContaCorrente(@PathVariable(value = "id") long id,
			@Valid @RequestBody Cliente novoCliente) {
		return new ResponseEntity<>(serviceCliente.alterarCliente(id, novoCliente), HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> getById(@PathVariable(value = "id") long id) {
		return new ResponseEntity<>(serviceCliente.getClientebyId(id), HttpStatus.OK);
	}
}
