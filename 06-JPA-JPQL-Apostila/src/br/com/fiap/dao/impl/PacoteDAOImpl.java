package br.com.fiap.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PacoteDAO;
import br.com.fiap.entity.Pacote;
import br.com.fiap.entity.Transporte;

public class PacoteDAOImpl extends GenericDAOImpl<Pacote,Integer> implements PacoteDAO{

	public PacoteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Pacote> listar() {
		TypedQuery<Pacote> query = em.createQuery("from Pacote", Pacote.class);
		return query.getResultList();
	}

	//Buscar pelo maior ou preco igual
	@Override
	public List<Pacote> buscarPorPrecoMaiorIgual(float preco) {
		
		//Criar typedQuery
		TypedQuery<Pacote> query = em.createQuery("from Pacote p where p.preco >= :churros", Pacote.class);		
		//Setar o parâmetro
		query.setParameter("churros", preco);
		//Executar a query
		return query.getResultList();
	}

	@Override
	public List<Pacote> buscarPorTransporte(Transporte transporte) {
		TypedQuery<Pacote> query = em.createQuery("from Pacote p where p.transporte = :t", Pacote.class);
		query.setParameter("t", transporte);
		return query.getResultList();
	}

	@Override
	public List<Pacote> buscaPorData(Calendar inicio, Calendar fim) {
		
		TypedQuery<Pacote> query = em.createQuery("from Pacote p where p.dataSaida = :dt", Pacote.class);
		query.setParameter("dt", "between" + inicio + "and" + fim);
		
		return query.getResultList();
	}

}
