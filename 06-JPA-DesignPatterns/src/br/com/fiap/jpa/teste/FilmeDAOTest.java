package br.com.fiap.jpa.teste;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.fiap.jpa.dao.FilmeDAO;
import br.com.fiap.jpa.dao.impl.FilmeDAOImpl;
import br.com.fiap.jpa.entity.Categoria;
import br.com.fiap.jpa.entity.Filme;
import br.com.fiap.jpa.exception.FailedCommitException;
import br.com.fiap.jpa.exception.KeyNotFoundException;

class FilmeDAOTest {
	
	private static FilmeDAO dao;
	private Filme filme;
	
	@BeforeAll //Método chamado antes de todos os testes
	static void inicializar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teste");
		EntityManager em = emf.createEntityManager();
		dao = new FilmeDAOImpl(em);
	}
	
	@BeforeEach //Método é executado antes de cada teste
	void cadastrar() {
		//Arrange
		filme = new Filme("Senhor dos aneis", 600, 
				new GregorianCalendar(2001, Calendar.JANUARY, 1), Categoria.AVENTURA);
		
		//Act
		try {
			dao.create(filme);
			dao.commit();
		} catch (FailedCommitException e) {
			fail("Falha no teste");
		}
	}
	
	@Test
	@DisplayName("Remove um filme com sucesso")
	void removerComSucessoTest() {
		//Act
		//Remover o filme
		try {
			dao.delete(filme.getCodigo());
			dao.commit();
		}catch(FailedCommitException | KeyNotFoundException e) {
			fail("Falha no teste");
		}
		//Assert
		//Pesquisar o filme
		//Validar se ocorre uma exceção na busca, já que o filme não é encontrado
		assertThrows(KeyNotFoundException.class, () -> { dao.read(filme.getCodigo()); } );
		
	}
	
	@Test
	@DisplayName("Atualiza um filme com sucesso")
	void atualizarComSucessoTest() {
		//Act - altera os valores para atualizar no banco
		filme.setTitulo("Harry Potter");
		filme.setDuracao(180);
		
		try {
			//Atualiza o filme no banco de dados
			dao.update(filme);
			dao.commit();
		} catch (FailedCommitException e) {
			fail("Falha no teste");
		}
		
		//Assert
		try {
			//Pesquisa o filme que foi atualizado
			Filme busca = dao.read(filme.getCodigo());
			//Verifica se as atualizações foram realizadas
			assertEquals("Harry Potter", busca.getTitulo());
			assertEquals(180, busca.getDuracao());
		} catch (KeyNotFoundException e) {
			fail("Falha no teste");
		}
	}
	
	@Test
	@DisplayName("Busca um filme com sucesso")
	void buscarComSucessoTest() {
		try {
			//Act
			Filme busca = dao.read(filme.getCodigo());
			//Assert
			assertNotNull(busca); //valida se encontrou algum resultado
			assertEquals("Senhor dos aneis", busca.getTitulo()); //valida se o resultado está ok
		} catch (KeyNotFoundException e) {
			fail("Falha no teste");
		}
	}

	@Test
	@DisplayName("Cadastra um filme com sucesso")
	void cadastrarComSucessoTest() {
		//Assert
		assertNotEquals(0, filme.getCodigo()); //valida se foi gerado um código para pk	
	}

}
