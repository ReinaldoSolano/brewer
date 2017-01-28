package com.algaworks.brewer.model;

public enum Volume {

	MINI("269ml"), PEQUENA("300ml"), MEDIA("355ml"), GRANDE("473ml"), LITRO("1000ml");

	private String descricao;

	private Volume(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
