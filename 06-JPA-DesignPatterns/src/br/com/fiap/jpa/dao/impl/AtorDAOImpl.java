package br.com.fiap.jpa.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.jpa.dao.AtorDAO;
import br.com.fiap.jpa.entity.Ator;

public class AtorDAOImpl extends GenericDAOImpl<Ator, Integer> implements AtorDAO {

	public AtorDAOImpl(EntityManager em) {
		super(em);
	}

}