package br.com.fiap.jpa.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.jpa.dao.UsuarioDAO;
import br.com.fiap.jpa.dao.impl.UsuarioDAOImpl;
import br.com.fiap.jpa.entity.Genero;
import br.com.fiap.jpa.entity.Login;
import br.com.fiap.jpa.entity.Patrocinio;
import br.com.fiap.jpa.entity.Post;
import br.com.fiap.jpa.entity.Usuario;
import br.com.fiap.jpa.exception.FailledCommitException;
import br.com.fiap.jpa.singleton.EntityManagerFactorySingleton;

public class View2 {
	
	//Cadastrar todas as entidades relacionadas
		public static void main(String[] args) {
	
		//Instanciar o EntityManagerFactory e EntityManager
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		//Instanciar o UsuarioDAOImpl
		UsuarioDAO dao = new UsuarioDAOImpl(em);
		
		//Instanciar um login
		Login lo = new Login("pedropiramboia@gmail.com", "1234pedro");
		
		//Instanciar um usuario com login
		Usuario usuario = new Usuario("Allen", Genero.MASCULINO, lo);
		
		//Instanciar 3 posts e add no usuario
		usuario.addPost(new Post("AM", "Parceria com a IBM"));
		usuario.addPost(new Post("NAC", "Será lançada hoje a tarde"));
		usuario.addPost(new Post("Grupos de AM", "Me mandem os grupos"));		
		
		//Instanciar uma lista de patrocinios e adicionar 2 patrocinios na lista
		List<Patrocinio> lista = new ArrayList<Patrocinio>();
		lista.add(new Patrocinio(100, Calendar.getInstance(), "FIAP"));
		lista.add(new Patrocinio(200, Calendar.getInstance(),"IBM"));
		
		//Setar a lista de patrocinio no post
		//Recupera o primeiro item da lista de post do usuário para setar a lista de patrocínio.
		usuario.getPosts().get(0).setPatrocinios(lista);
		
		//Cadastrar o usuario
		dao.creat(usuario);
		
		//Commit
		try {
			dao.commit();
			System.out.println("Sucesso!");
		}catch(FailledCommitException e) {
			System.out.println("Erro..");
		}
		em.close();
		fabrica.close();	
	}

}
