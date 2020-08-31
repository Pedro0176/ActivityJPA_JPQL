package br.com.fiap.jpa.view;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.jpa.dao.AtorDAO;
import br.com.fiap.jpa.dao.impl.AtorDAOImpl;
import br.com.fiap.jpa.entity.AreaAtuacao;
import br.com.fiap.jpa.entity.Ator;
import br.com.fiap.jpa.exception.FailedCommitException;
import br.com.fiap.jpa.exception.KeyNotFoundException;
import br.com.fiap.jpa.singleton.EntityManagerFactorySingleton;

public class ConsoleViewAtor {
	
	public static void main(String[] args) {
		
		//Criar a fábrica
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		
		//Instanciar o entity manager
		EntityManager em = fabrica.createEntityManager();
		
		//Intanciar o AtorDAO
		AtorDAO dao = new AtorDAOImpl(em);
		
		Ator a = new Ator("Pedro de Sousa Martins", Calendar.getInstance(), 171, AreaAtuacao.COMEDIA);
		
		//Cadastar um ator
		try {
			dao.create(a);
			dao.commit();	
		}catch(FailedCommitException e) {
			System.out.println("Não cadastrado");
		}
		
		//Pesquisar um ator
		try {
			Ator buscar = dao.read(1);
			System.out.println(buscar.getNome());
			
		}catch(KeyNotFoundException e) {
			System.out.println("Ator não encontrado");
		}
		
		//Atualizar um ator
		
		try {
			a.setNome("Pedro Atualizado!!");
			dao.update(a);
			dao.commit();
			System.out.println("Atualizado com Sucesso.");
		}catch(FailedCommitException e) {
			System.out.println("Falha ao atualizar");
		}

		//Remover um ator
		try {
			dao.delete(1);
			dao.commit();		
		}catch(KeyNotFoundException e) {
			System.out.println("Chave não encontrada");
		}catch(FailedCommitException e) {
			System.out.println("Não removido!");
		}
		
		//Fechar as paradas
		em.close();
		fabrica.close();
		
	}

}
