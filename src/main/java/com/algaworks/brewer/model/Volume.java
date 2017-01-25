package com.algaworks.brewer.model;

public enum Volume {
	
	MINI("269ml"), PEQUENA("300ml"), MEDIA("300ml"), FRUTADA("Frutada"), SUAVE("Suave");

	private String descricao;

	private Volume(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
