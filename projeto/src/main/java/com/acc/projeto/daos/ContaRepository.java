package com.acc.projeto.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.projeto.entitys.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
