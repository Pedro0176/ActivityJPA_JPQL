package br.com.fiap.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.dao.impl.ReservaDAOImpl;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ReservaView {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = EntityManagerFactorySingleton.getInstance();
		EntityManager em = fabrica.createEntityManager();
		
		
		ReservaDAO dao = new ReservaDAOImpl(em);
		
		
		System.out.println("QUANTIDADE DE RESERVA DO CLIENTE 2: " + dao.contaPorCliente(2));
		
		dao.buscarPorDescricaoPacote("Cancun").forEach(r ->
		System.out.println(r.getId()+ " "+r.getPacote().getDescricao()));
		
		System.out.println("Média dias por cliente: " + dao.calcularMediaDiasPorCliente("a"));
		
		em.close();
		fabrica.close();
		
	}
	
	
	
}
