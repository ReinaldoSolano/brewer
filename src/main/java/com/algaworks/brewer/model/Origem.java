package com.algaworks.brewer.model;

public enum Origem {
	NACIONAL("Nacional"), IMPORTADA("Importada");

	private String descricao;

	private Origem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
