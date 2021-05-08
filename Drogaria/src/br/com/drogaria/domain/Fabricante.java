package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


import javax.validation.constraints.Size;

@Entity // ENTIDADE DE PERSISTENCIA
@Table(name = "tbl_fabricantes") // mapeando a tabela fabricantes

//vetor de namedqueries, ou seja, pode ter varias consultas dentro dele
@NamedQueries ({
	@NamedQuery(name = "Fabricante.listar", query = "SELECT fabricante FROM Fabricante fabricante"),
	@NamedQuery(name = "Fabricante.buscarPorCodigo", query = "SELECT fabricante FROM Fabricante fabricante WHERE fabricante.codigo = :codigo")
	}) 


public class Fabricante {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fab_codigo")
	private int codigo;

	@NotEmpty (message = "O campo descrição é obrigatório") // mensagem de erro para quando o usuario não digitar no campo
	@Size (min = 5, max = 50, message = "Tamanho inválido para o campo descrição (5 - 50)")
	@Column(name = "fab_descricao", length = 50, nullable = false) // mapeamento para a tabela (mapeamento objeto
																	// relacional) MOR
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int l) {
		this.codigo = l;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// imprime os fabricantes
	@Override
	public String toString() {
		return "Fabricante [codigo=" + codigo + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		Fabricante other = (Fabricante) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	
}

//primeira entidade de persistencia