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

	/*
	@Override
	public List<Cliente> listar() {
		//Criar a query
		TypedQuery<Cliente> query = em.createQuery("from Cliente", Cliente.class);
		//Executar
		return query.getResultList();
	}
	*/
	
	@Override
	public List<Cliente> buscarPorNome(String nome) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :n",Cliente.class);
		query.setParameter("n", "%"+ nome + "%");
		query.setMaxResults(50); //configura o máximo de resultados
		return query.getResultList();
	}
	
	@Override
	public List<Cliente> buscarPorNome(String nome, int maximo, int primeiraPosicao) {
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where upper(c.nome) like :n",Cliente.class);
		query.setParameter("n", "%"+ nome.toUpperCase() + "%");
		query.setMaxResults(maximo);//configura o máximo de resultados
		query.setFirstResult(primeiraPosicao);//configura a primeira posição do retorno
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorEstado(String estado) {
		TypedQuery<Cliente> query = 
			em.createQuery("from Cliente c where c.endereco.cidade.uf = :es",Cliente.class);
		query.setParameter("es", estado);
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorDiasReserva(int dias) {
		TypedQuery<Cliente> query = 
			em.createQuery("select r.cliente from Reserva r where r.numeroDias = :d",Cliente.class);
		query.setParameter("d", dias);
		return query.getResultList();
	}

	//Retornar somente o nome e o cpf
	@Override
	public List<Cliente> buscarPorNomeEstado(String nome, String estado) {

	TypedQuery<Cliente> query = em.createQuery("select new Cliente(c.nome, c.cpf) from Cliente c where c.nome like :n and c.endereco.cidade.uf = :u", Cliente.class);
		query.setParameter("n", "%" + nome + "%");
		query.setParameter("u", estado);
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscar(String nome, String cidade) {
		
		TypedQuery<Cliente> query = em.createQuery("from Cliente c where c.nome like :n and c.endereco.cidade.nome like :c",Cliente.class);
		query.setParameter("n", "%" + nome + "%");
		query.setParameter("c", "%" + cidade + "%");
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarPorEstados(List<String> estados) {
		

		return em.createQuery("from Cliente c where c.endereco.cidade.uf in :n ", Cliente.class).
				setParameter("n", estados)
				.getResultList();
	}

	@Override
	public long qtdClientePorEstado(String estado) {
		
		return em.createQuery("select count(c) from Cliente c where c.endereco.cidade.uf = :nome", Long.class).setParameter("nome", estado)
				.getSingleResult();
	}
	
}
