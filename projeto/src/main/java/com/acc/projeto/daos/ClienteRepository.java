package com.acc.projeto.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acc.projeto.entitys.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
