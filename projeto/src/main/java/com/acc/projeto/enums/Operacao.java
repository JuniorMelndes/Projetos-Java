package com.acc.projeto.enums;

public enum Operacao {
	TRANSFERENCIA_DEBITO("transferencia-debito"),
	TRANSFERENCIA_CREDITO("transferencia-credito"),
	DEPOSITO("deposito"),
	SAQUE("saque");
	
	private String descricao;
	
	Operacao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
