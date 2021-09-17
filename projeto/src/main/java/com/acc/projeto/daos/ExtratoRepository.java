package com.acc.projeto.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.projeto.entitys.Extrato;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

}
