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
@Table(name = "TB_ATOR")
@SequenceGenerator(name = "ator", sequenceName = "SQ_TB_ATOR", allocationSize = 1)
public class Ator {
	
	@Column(name = "cd_ator")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ator")
	private int codigo;
	
	@Column(name = "nm_ator", nullable = false)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Calendar nascimento;
	
	@Column(name = "ds_altura")
	private float altura;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_area_atuacao")
	private AreaAtuacao area;
	

	public Ator() {
		super();
			
		}

	
	public Ator(String nome, Calendar nascimento, float altura, AreaAtuacao area) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
		this.altura = altura;
		this.area = area;
	}






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

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public AreaAtuacao getArea() {
		return area;
	}

	public void setArea(AreaAtuacao area) {
		this.area = area;
	}
	
	
	
}
