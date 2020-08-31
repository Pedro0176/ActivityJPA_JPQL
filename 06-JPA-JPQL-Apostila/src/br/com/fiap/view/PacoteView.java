package br.com.fiap.view;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.dao.TransporteDAO;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.dao.impl.PacoteDAOImpl;
import br.com.fiap.dao.impl.TransporteDAOImpl;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class PacoteView {
	
	public static void main(String[] args) {
		
		GregorianCalendar g = new GregorianCalendar(2020, Calendar.JANUARY,1);
		
		//Instanciar o EntityManagerFactory e o EntityManager
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		//Instanciar um dao do cliente
		ClienteDAO dao = new ClienteDAOImpl(em);
		PacoteDAO pDao = new PacoteDAOImpl(em);
		
		
		System.out.println("LISTAR");
		List<Cliente> lista = dao.listar();
		//Exibir lista de clientes
		lista.forEach(item -> System.out.println(item.getNome()));	
		
		System.out.println("-----------BUSCAR POR PARTE DO NOME-----------");
		lista = dao.buscarPorNome("a", 2, 0);
		lista.forEach(cliente -> System.out.println(cliente.getNome()));
		
		System.out.println("BUSCAR POR TRANSPORTE");
		List<Pacote> listaPacote = pDao.listar();
		TransporteDAO tDao = new TransporteDAOImpl(em);
		Transporte transporte = tDao.pesquisar(1);		
		listaPacote = pDao.buscarPorTransporte(transporte);
		listaPacote.forEach(item -> System.out.println(item.getDescricao() + " " + item.getTransporte().getEmpresa()));
		
	
		
		
		System.out.println("-----------BUSCAR POR PREÇO MAIOR-----------");
		listaPacote = pDao.buscarPorPrecoMaiorIgual(2200); //pesquisa os pacotes com preço maior que 10
		//Exibir a descrição e o preço dos pacotes
		listaPacote.forEach(p -> { System.out.println(p.getDescricao()+ " " + p.getPreco()); });
		
		
	
		System.out.println("-----------BUSCAR POR ESTADO-----------");
		lista = dao.buscarPorEstado("PR	");
		lista.forEach(cliente ->  System.out.println(cliente.getNome() + " "+ cliente.getEndereco().getCidade().getUf()));
		
		
		System.out.println("-----------CLIENTES QUE EFETURAM RESERVAS EM UMA QUANTIDADE DE DIAS ESPECÍFICA-----------");
		lista = dao.buscarPorDiasReserva(10);
		lista.forEach(item -> System.out.println(item.getNome()));
		
		
		System.out.println("--------------BUSCAR POR DATAS--------------");
		//Arrumar isso aqui, deu erro ...
		
		listaPacote = pDao.buscaPorData(g , g);
		listaPacote.forEach(item -> System.out.println(item.getDataSaida()));
		
		
		//Fechar
		em.close();
		fabrica.close();
		
		
	}

}
