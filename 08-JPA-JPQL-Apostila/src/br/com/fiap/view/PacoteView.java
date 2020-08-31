package br.com.fiap.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.dao.TransporteDAO;
import br.com.fiap.dao.impl.PacoteDAOImpl;
import br.com.fiap.dao.impl.TransporteDAOImpl;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class PacoteView {

	public static void main(String[] args) {
		
		
		
		//Instanciar o EntityManagerFactory e o Entity Manager
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		//Instanciar um dao do Pacote
		PacoteDAO dao = new PacoteDAOImpl(em);
		
		System.out.println("LISTAR");
		//Pesquisar todos os pacotes
		List<Pacote> lista = dao.listar();
		
		//Exibir a descrição dos pacotes
		for (Pacote pacote : lista) {
			System.out.println(pacote.getDescricao());
		}
		
		//Lambda
		//lista.forEach(pacote -> { System.out.println(pacote.getDescricao()); });
		
		System.out.println("BUSCAR POR PREÇO MAIOR");
		lista = dao.buscarPorPrecoMaiorIgual(2200); //pesquisa os pacotes com preço maior que 10
		//Exibir a descrição e o preço dos pacotes
		lista.forEach(p -> { System.out.println(p.getDescricao() + " " + p.getPreco()); });
		
		System.out.println("BUSCAR POR TRANSPORTE");
		TransporteDAO tDao = new TransporteDAOImpl(em); //Instancia o DAO do transporte
		Transporte transporte = tDao.pesquisar(2); //Pesquisa o transporte de código 1
		lista = dao.buscarPorTransporte(transporte);
		lista.forEach(item -> 
			System.out.println(item.getDescricao() + " " + item.getTransporte().getEmpresa()));
		
		System.out.println("BUSCAR POR DATAS");
		Calendar inicio = new GregorianCalendar(2017, Calendar.JANUARY, 1);
		Calendar fim = new GregorianCalendar(2017, Calendar.DECEMBER, 31);
		
		lista = dao.buscarPorDatas(inicio,fim);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		lista.forEach(p -> System.out.println( p.getDescricao() + " " + sdf.format(p.getDataSaida().getTime())));
																				
																			  //esse transporte a gnt reaproveitou doq tem l� em cima j�
		System.out.println("SOMAR PRECO PACOTES " + dao.somarPrecoPorTransporte(transporte));
		
		//Fechar
		em.close();
		fabrica.close();
		
	}
	
}