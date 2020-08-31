package br.com.fiap.jpa.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_PATROCINIO")
@SequenceGenerator(name="pat", sequenceName = "SQ_TB_PATROCINIO", allocationSize = 1)
public class Patrocinio {

	@Id
	@Column(name="cd_patrocinio")
	@GeneratedValue(generator = "pat", strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	@Column(name="vl_patrocinio", nullable = false)
	private double valor;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_patrocinio")
	private Calendar data;
	
	@Column(name="nm_patrocinio", nullable = false, length = 100)
	private String nome;
	
	//Relacionamento bidirecional muitos-para-muitos
	//mappedBy = nome do atributo que mapeia a tabela associativa (na tabela Post)
	@ManyToMany(mappedBy = "patrocionio")
	private List<Post> posts;

	public Patrocinio() {
		super();
	}
	
	public Patrocinio(double valor, Calendar data, String nome) {
		super();
		this.valor = valor;
		this.data = data;
		this.nome = nome;
	}




	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPost(List<Post> posts) {
		this.posts = posts;
	}
	
}
