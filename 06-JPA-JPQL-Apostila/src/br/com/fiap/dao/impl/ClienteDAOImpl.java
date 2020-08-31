package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.entity.Cliente;

public class ClienteDAOImpl extends GenericDAOImpl<Cliente,Integer> implements ClienteDAO{

	public ClienteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Cliente> listar() {
		//Criar a query
		TypedQuery<Cliente> query = em.createQuery("from Cliente", Cliente.class);
		//Executar
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorNome(String nome) {

		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :n", Cliente.class);
		
		query.setParameter("n", "%" + nome + "%");
		query.setMaxResults(50); //Configura o máximo de resultados
		
		return query.getResultList();
	}

	
	@Override
	public List<Cliente> buscarPorNome(String nome, int maximo, int primeiraPosicao) {

		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :n", Cliente.class);
		
		query.setParameter("n", "%" + nome + "%");
		query.setMaxResults(maximo); //Configura o máximo de resultados
		query.setFirstResult(primeiraPosicao);
		
		return query.getResultList();
	}
	
	
	@Override
	public List<Cliente> buscarPorEstado(String estado) {
		
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.endereco.cidade.uf = :es", Cliente.class);
		
		query.setParameter("es", estado);
		
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorDiasReserva(int dias) {
		
		TypedQuery<Cliente> query = em.createQuery("select r.cliente from Reserva r where r.numeroDias = :d", Cliente.class);
		query.setParameter("d", dias);
		
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorNomeeCidade(String nome, String cidade) {
		
		TypedQuery<Cliente> query = em.createQuery("", Cliente.class);
		return null;
	}

	

}
