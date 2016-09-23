package com.algaworks.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.validation.SKU;

@Entity
@Table(name = "cerveja")
public class Cerveja implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6804566886516311617L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bw_codigo")
	private Long codigo;

	@SKU
	@NotBlank(message = "SKU é obrigatório")
	@Column(name = "bw_sku")
	private String sku;

	@NotBlank(message = "Nome é obrigatório")
	@Column(name = "bw_nome")
	private String nome;

	@Size(min = 1, max = 50, message = "Descrição deve ter entre 5 e 100 caracteres")
	@Column(name = "bw_descricao")
	private String descricao;

	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "3.00", message = "Valor mínimo da cerveja é R$3,00")
	@DecimalMax(value = "100.00", message = "Valor máximo da cerveja é R$100,00")
	@Column(name = "bw_valor")
	private BigDecimal valor;

	@NotNull(message = "Teor Alcoólico é obrigatório")
	@DecimalMin(value = "1.00", message = "Teor Alcoólico mínimo da cerveja é 1,00%")
	@DecimalMax(value = "15.00", message = "Teor Alcoólico máximo da cerveja é 15,00%")
	@Column(name = "bw_teor_alcoolico")
	private BigDecimal teorAlcoolico;

	@NotNull(message = "Comissão é obrigatória")
	@DecimalMin(value = "5.00", message = "Comissão mínima do produto é 5,00%")
	@DecimalMax(value = "30.00", message = "Comissão máximo do produto é 30,00%")
	@Column(name = "bw_comissao")
	private BigDecimal comissao;

	@NotNull(message = "Quantidade no estoque é obrigatório")
	@Column(name = "bw_quantidade_estoque")
	private Integer quantidadeEstoque;

	@NotNull(message = "Selecione a origem da cerveja")
	@Enumerated(EnumType.STRING)
	@Column(name = "bw_origem")
	private Origem origem;

	@NotNull(message = "Selecione o sabor da cerveja")
	@Column(name = "bw_sabor")
	@Enumerated(EnumType.STRING)
	private Sabor sabor;

	@NotNull(message = "Selecione o estilo da cerveja")
	@ManyToOne
	@JoinColumn(name = "bw_codigo_estilo")
	private Estilo estilo;

	@Column(name = "bw_foto")
	private String foto;

	@Column(name = "bw_content_type")
	private String contentType;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getFotoOuMock(){
		return !StringUtils.isEmpty(foto)?foto:"cerveja-mock.png";
	}

	@PrePersist
	@PreUpdate
	public void prePersistUpdate() {
		sku = sku.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
