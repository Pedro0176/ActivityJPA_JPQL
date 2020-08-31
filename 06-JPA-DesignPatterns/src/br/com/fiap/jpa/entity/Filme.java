package br.com.fiap.jpa.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
TB_FILME
	*cd_filme NUMBER (PK)
	*ds_titulo VARCHAR(50)
	ds_duracao NUMBER
	dt_lancamento DATE (Calendar no Java)
	ds_categoria VARCHAR (Enum no Java)
 */
@Entity
@Table(name="TB_FILME")
@SequenceGenerator(name = "filme", sequenceName = "SQ_TB_FILME", allocationSize = 1)
public class Filme {

	@Id
	@Column(name="cd_filme")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filme")
	private int codigo;
	
	@Column(name="ds_titulo", nullable = false, length = 50)
	private String titulo;
	
	@Column(name="ds_duracao")
	private int duracao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_lancamento")
	private Calendar dataLancamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_categoria")
	private Categoria categoria;
	
	public Filme() {
		super();
	}

	public Filme(String titulo, int duracao, Calendar dataLancamento, Categoria categoria) {
		super();
		this.titulo = titulo;
		this.duracao = duracao;
		this.dataLancamento = dataLancamento;
		this.categoria = categoria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}