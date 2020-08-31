package br.com.fiap.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.impl.ClienteDAOImpl;
import br.com.fiap.entity.Cliente;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ClienteView {

	public static void main(String[] args) {
		
		new GregorianCalendar(2010, Calendar.JANUARY, 1);
		
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		ClienteDAO dao = new ClienteDAOImpl(em);
		
		System.out.println("LISTAR");
		List<Cliente> lista = dao.listar();
		lista.forEach(item -> System.out.println(item.getNome()));
		
		System.out.println("BUSCAR POR PARTE DO NOME");
		lista = dao.buscarPorNome("A", 2, 1);
		lista.forEach(cliente -> System.out.println(cliente.getNome()));
		
		System.out.println("BUSCAR POR ESTADO");
		lista = dao.buscarPorEstado("PR");
		lista.forEach(cliente -> 
			System.out.println(cliente.getNome() + " " + cliente.getEndereco().getCidade().getUf()));
		
		System.out.println("BUSCAR POR DIAS RESERVA");
		lista = dao.buscarPorDiasReserva(10);
		lista.forEach(item -> System.out.println(item.getNome()));
		
		System.out.println("BUSCAR POR NOME E ESTADO");
		lista= dao.buscarPorNomeEstado("a", "PR");
		lista.forEach(c -> System.out.println(c.getNome() + " " + c.getCpf() + " " + c.getDataNascimento()));
		
		System.out.println("BUSCAR");
		lista = dao.buscar("a", "a");
		lista.forEach(c -> System.out.println(c.getNome() + " " + c.getEndereco().getCidade().getNome()));
		
		System.out.println("BUSCAR POR ESTADOS");
		List<String> listaestados = new ArrayList<String>();
		listaestados.add("SP");
		listaestados.add("BA");
		lista = dao.buscarPorEstados(listaestados);
		lista.forEach(c -> System.out.println(c.getNome() + " " + c.getEndereco().getCidade().getNome()));
		
		System.out.println("QTD CLIENTE EM SP: " + dao.qtdClientePorEstado("PR"));
		
		
		em.close();
		fabrica.close();
	}

}
