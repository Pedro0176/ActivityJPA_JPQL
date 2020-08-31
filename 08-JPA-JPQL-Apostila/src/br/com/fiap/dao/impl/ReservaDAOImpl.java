package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.entity.Reserva;

public class ReservaDAOImpl extends GenericDAOImpl<Reserva,Integer> implements ReservaDAO {

	public ReservaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public long contaPorCliente(int idCliente) {

			
		return em.createQuery("select count(r) from Reserva r where r.cliente.id = :id", Long.class).setParameter("id", idCliente)
				.getSingleResult();
	}
	
	
	//Esses dois m�todos abaixo foram feitos l� na classe bean a pesquisa, aqui n�s estamos s� puxando oque j� est� l�.
	@Override
	public List<Reserva> buscarPorDescricaoPacote(String descricao) {
		return em.createNamedQuery("Reserva.porDescricaoPacote", Reserva.class)
				.setParameter("d", "%"+descricao+"%")
				.getResultList();
	}

	@Override
	public double calcularMediaDiasPorCliente(String nome) {
		
		return em.createNamedQuery("Reserva.mediaDiasPorCliente", Double.class).setParameter("n", "%" + nome + "%").getSingleResult();
	}

}
