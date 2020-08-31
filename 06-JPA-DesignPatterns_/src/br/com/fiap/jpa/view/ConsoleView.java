package br.com.fiap.jpa.view;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.jpa.dao.FilmeDAO;
import br.com.fiap.jpa.dao.impl.FilmeDAOImpl;
import br.com.fiap.jpa.entity.Categoria;
import br.com.fiap.jpa.entity.Filme;
import br.com.fiap.jpa.exception.FailedCommitException;
import br.com.fiap.jpa.exception.KeyNotFoundException;
import br.com.fiap.jpa.singleton.EntityManagerFactorySingleton;

public class ConsoleView {

	public static void main(String[] args) {
		
		//Obter uma instância da fábrica
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		//Instanciar o FilmeDAOImpl
		FilmeDAO dao = new FilmeDAOImpl(em);
		
		//Criar uma instancia de Filme
		Filme f = new Filme("A volta dos que não foram", 154, Calendar.getInstance(), Categoria.AVENTUREIRO);
		
		//Cadastrar um filme
		try {
		dao.create(f);
		dao.commit();
		System.out.println("Filme Cadastrado!");
		}catch(FailedCommitException e) {
			System.out.println("Erro ao cadastrar...");		
		}
		
		//Pesquisar um filme
		try {
		Filme busca = dao.read(1);
		System.out.println(busca.getTitulo());
		}catch(KeyNotFoundException e) {
			System.out.println("Filme não encontrado...");
		}
		
		//Atualizar
		try {
		f.setTitulo("Carros");
		dao.update(f);
		dao.commit();		
		System.out.println("Atualizado com sucesso");
		}catch(FailedCommitException e) {
			System.out.println("Não atualizado...");
		}
		
		//Remover
		try {
		dao.delete(1);
		dao.commit();
		}catch(KeyNotFoundException e) {
			System.out.println("Chave não encontrada para deletar");
		}catch(FailedCommitException e) {
			System.out.println("Erro ao remover o filme");
		}
		//Fecha
		em.close();
		fabrica.close();
		
	}
	
	
}
