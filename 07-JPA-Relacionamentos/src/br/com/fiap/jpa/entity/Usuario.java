package br.com.fiap.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_USUARIO")
@SequenceGenerator(name="usuario", sequenceName = "SQ_TB_USUARIO", allocationSize = 1)
public class Usuario {

	@Id
	@Column(name="cd_usuario")
	@GeneratedValue(generator = "usuario", strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	@Column(name="nm_usuario", nullable = false, length = 80)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_genero", nullable = false)
	private Genero genero;
	
	
	//Mapear o relacionamento bidirecional (um-para-muitos)
	//Um usu�rio para v�rios posts
	//mappedBy = "usuario" nome do atributo na classe Post que mapeia a FK;
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
	private List<Post> posts = new ArrayList<Post>();
	
	
	//Mapeando o relacioamento com o login
	//cascade -> realiza as a��es configuradas na rela�a�
	//fech -> determina o momento que ser� carregada a rela��o
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})	
	@JoinColumn(name = "cd_login", nullable = false)
	private Login login;
	
	//M�todo para adicionar post no usu�rio
	//M�todo utilizado somente na rela��o OneToMany
	public void addPost(Post post) {
		//esse this fala que o usuario do post, � a pr�pria classe
		post.setUsuario(this);
		//adiciona o post na lista
		posts.add(post);
	}
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nome, Genero genero, Login login) {
		super();
		this.nome = nome;
		this.genero = genero;
		this.login = login;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
