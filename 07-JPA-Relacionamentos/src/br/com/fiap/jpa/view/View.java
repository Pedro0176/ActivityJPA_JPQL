package br.com.fiap.jpa.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.jpa.entity.Genero;
import br.com.fiap.jpa.entity.Login;
import br.com.fiap.jpa.entity.Post;
import br.com.fiap.jpa.entity.Usuario;

public class View {
	
public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("oracle");
		EntityManager em = fabrica.createEntityManager();
		
		//Cadastrar o login e o usuário
		Login login = new Login("username@admin.com", "132456");
		Usuario user = new Usuario("User Name",Genero.FEMININO, login );
		
		Post p1 = new Post("Aviso NAC", "NAC sera aplicada logo mais");
		Post p2 = new Post("Grupos AM", "Informar nome de cada integrante");
		
		user.addPost(p1);
		user.addPost(p2);
		
		//Cadastra login e o usuário
		//em.persist(login);
		em.persist(user);
		
		
		
		
		
		//efetiva no banco de dados
		em.getTransaction().begin();
		em.getTransaction().commit();
		
		//Removendo o usuário de código (1 PK)
		//Usuario busca = em.find(Usuario.class, 1);
		//em.remove(busca);
		
		//aQUI FEZ O Fetch.EAGER
		//System.out.println(usuario.getNome());
		//System.out.println(usuario.getLogin().getEmail());
		
		em.close();
		fabrica.close();
		

	}

}
