package com.algaworks.brewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9203080112075907085L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bw_codigo")
	private Long codigo;

	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 30, message = "Nome deve ter no máximo {max} caracteres")
	@Column(name = "bw_nome")
	private String nome;

	@NotBlank(message = "A sigla é obrigatória")
	@Size(max = 2, message = "Sigla deve ter no máximo {max} caracteres")
	@Column(name = "bw_sigla")
	private String sigla;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
