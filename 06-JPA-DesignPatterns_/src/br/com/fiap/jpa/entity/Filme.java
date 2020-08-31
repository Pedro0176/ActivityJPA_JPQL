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

@Entity
@Table(name = "TB_FILME")
@SequenceGenerator(name="filme", sequenceName = "SQ_TB_FILME", allocationSize = 1)
public class Filme {
	@Id
	@Column(name="cd_filme")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filme")
	private int codigo;
	
	@Column(name="ds_titulo", length=50, nullable = false)
	private String titulo;
	
	@Column(name="ds_duracao")
	private int duracao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_lancamento")
	private Calendar lancamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_categoria")
	private Categoria categoria;
	
	
	public Filme() {
		super();
		
	}
	
	public Filme(String titulo, int duracao, Calendar lancamento, Categoria categoria) {
		super();
		this.titulo = titulo;
		this.duracao = duracao;
		this.lancamento = lancamento;
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
	public Calendar getLancamento() {
		return lancamento;
	}
	public void setLancamento(Calendar lancamento) {
		this.lancamento = lancamento;
	}
	
}
