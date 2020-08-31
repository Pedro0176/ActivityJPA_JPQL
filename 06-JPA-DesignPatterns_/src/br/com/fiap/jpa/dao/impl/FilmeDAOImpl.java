package br.com.fiap.jpa.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.jpa.dao.FilmeDAO;
import br.com.fiap.jpa.entity.Filme;

public class FilmeDAOImpl extends GenericDAOImpl<Filme, Integer> implements FilmeDAO{

	public FilmeDAOImpl(EntityManager em) {
		super(em);

	}
	
	

}
