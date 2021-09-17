package com.acc.projeto.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.projeto.entitys.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

}
