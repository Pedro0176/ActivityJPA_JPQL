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

/*
 * TB_ATOR
	*cd_ator NUMBER (PK)
	*nm_ator VARCHAR2(50)
	dt_nascimento DATE (Calendar no Java)
	ds_altura FLOAT
	ds_area_atuacao VARCHAR2 (Enum no Java)
	
O código será gerado por uma sequence
SQ_TB_ATOR	
 */
@Entity
@Table(name="TB_ATOR")
@SequenceGenerator(name="ator",sequenceName = "SQ_TB_ATOR",allocationSize = 1)
public class Ator {

	@Id
	@Column(name="cd_ator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ator")
	private int codigo;
	
	@Column(name="nm_ator", nullable = false, length = 50)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_nascimento")
	private Calendar dataNascimento;
	
	@Column(name="ds_altura")
	private float altura;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_area_atuacao")
	private AreaAtuacao areaAtuacao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
}
