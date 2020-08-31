package br.com.fiap.jpa.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	
	@BeforeAll //Método chamado antes de todos os teste.
	static void inicializar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("teste");
		EntityManager em = fabrica.createEntityManager();	
		dao = new FilmeDAOImpl(em);

	}
	
	@BeforeEach //Método executado antes de cada teste
	void cadastrar() {
		
		//Arrange
				filme = new Filme("Senhor dos Anéis", 600, new GregorianCalendar(2001, Calendar.JANUARY,1), Categoria.AVENTUREIRO);
				
				//Act
				try {
					dao.create(filme);
					dao.commit();
				}catch(FailedCommitException e) {
					fail("Falha no teste");					
		}
	}
	
	@Test
	@DisplayName("Atualiza um filme com sucesso")
	void atualizarComSucessoTest() throws FailedCommitException {
		
		
		//Act
		//Altera os valores para atualizar o banco de dados.
		filme.setTitulo("Harry Potter");
		filme.setDuracao(180);
		try {
			//Atualiza o filme no banco de dados
		dao.update(filme);
		dao.commit();
		}catch(FailedCommitException e) {
			fail("Falha no teste");
		}
		
		//Assert
			
			try {
				//Pesquisa o filme que foi atualizado 
				Filme busca = dao.read(filme.getCodigo());
				//Verifica se as atualizaçoes foram realizadas
				assertEquals("Harry Potter", busca.getTitulo());
				assertEquals(180, busca.getDuracao());
			} catch (KeyNotFoundException e) {
				fail("Falha no teste");
			}
		}
	
	
	@Test
	@DisplayName("Remove um filme com sucesso")
	void removerComSucessoTest(){
		//Act
		//Remover o filme
		try {
			dao.delete(filme.getCodigo());
			dao.commit();
		} catch (FailedCommitException | KeyNotFoundException e) {
			fail("Falha no teste");
		}
		
		//Pesquisar o filme
		//Validar se ocorre uma exceção na busca, já que o filme nao é encontrado
		//assertThrows = Pesquisa se esta certo;
		// KeyNotFoundException = Procura se tem algum erro
		// E depois temos a expressão lambda que roda o código para ler se tem algum codigo nulo.
		assertThrows(KeyNotFoundException.class, () -> {dao.read(filme.getCodigo());});
		try {
			Filme busca = dao.read(filme.getCodigo());
			//Assert	
			assertEquals(1, busca.getCodigo());
		} catch (KeyNotFoundException e) {
			// TODO Auto-generated catch block
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
			assertNotNull(busca); // Valida se encontrou algum resultado
			assertEquals("Senhor dos anéis", busca.getTitulo()); // valida se o resultado está OK	
		} catch (KeyNotFoundException e) {
			fail("Falha no teste");		
		}		
	}
	
	
	
	@Test
	void cadastrarComSucesso() {
		
		//Assert
		assertNotEquals(0, filme.getCodigo()); //valida se foi gerado um código para a PK
		
	}

}
