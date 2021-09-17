package com.acc.projeto.entitys;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.acc.projeto.enums.Operacao;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Extrato {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Operacao operacao;
	
	@Column(nullable = false)
	private Double valorDaOperacao;
	
	@Column
	private LocalDateTime dataMovimentacao;
	
	@Column(nullable = false)
	private Double saldoResultante;
	
	@Column
	private long idContaDestino;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "conta_id",  nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("conta_id")
	private Conta conta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Operacao getOpercao() {
		return operacao;
	}
	public void setOpercao(Operacao opercao) {
		this.operacao = opercao;
	}
	public Double getValorDaOperacao() {
		return valorDaOperacao;
	}
	public void setValorDaOperacao(Double valorDaOperacao) {
		this.valorDaOperacao = valorDaOperacao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta novaConta) {
		conta = novaConta;
	}
	
	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}
	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
	public long getIdContaDestino() {
		return idContaDestino;
	}
	public void setIdContaDestino(long idContaDestino) {
		this.idContaDestino = idContaDestino;
	}
	public Double getSaldoResultante() {
		return saldoResultante;
	}
	public void setSaldoResultante(Double saldoResultante) {
		this.saldoResultante = saldoResultante;
	}
	
	
	
}
