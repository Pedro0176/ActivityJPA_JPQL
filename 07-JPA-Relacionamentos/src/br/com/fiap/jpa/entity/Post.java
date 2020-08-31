package br.com.fiap.jpa.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_POST")
@SequenceGenerator(name="post", sequenceName = "SQ_TB_POST", allocationSize = 1)
public class Post {

	@Id
	@Column(name="cd_post")
	@GeneratedValue(generator = "post", strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	@Column(name="ds_titulo", length = 40, nullable = false)
	private String titulo;
	
	@Column(name="ds_post", length = 255, nullable = false)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_publicacao")
	private Calendar dataPublicacao;
	
	@Lob
	@Column(name="fl_foto")
	private byte[] foto;
	
	//Da classe que estamos para o relacionamento (Vários post para um usuário)
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cd_usuario", nullable = false)
	private Usuario usuario;
	
	//Mapear o relacionamento muito-para-muitos(tabela associativa)
	//JoinTable configura a tabela associativa (name -> nomeTabela)
	//joinColumns -> configura a coluna que armazena a FK da classe que estamos
	//inverseJoinColumns -> configura a coluna que armazena a FK do outro lado da relação
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "TB_POST_PATROCINIO", joinColumns = @JoinColumn(name="cd_post", nullable = false),
				inverseJoinColumns = @JoinColumn(name = "cd_patrocinio", nullable = false))
	private List<Patrocinio> patrocinios;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(String titulo, String descricao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Patrocinio> getPatrocinios() {
		return patrocinios;
	}

	public void setPatrocinios(List<Patrocinio> patrocinios) {
		this.patrocinios = patrocinios;
	}
	
}
