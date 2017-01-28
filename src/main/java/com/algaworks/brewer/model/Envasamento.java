package com.algaworks.brewer.model;

public enum Envasamento {

	LATA("Lata"), GARRAFA("Garrafa");

	private String descricao;

	private Envasamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
