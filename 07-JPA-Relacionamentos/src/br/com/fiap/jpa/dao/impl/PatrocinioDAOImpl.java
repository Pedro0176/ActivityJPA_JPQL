package br.com.fiap.jpa.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.jpa.dao.PatrocinioDAO;
import br.com.fiap.jpa.entity.Patrocinio;

public class PatrocinioDAOImpl extends GenericDAOImpl<Patrocinio, Integer> implements PatrocinioDAO {

	public PatrocinioDAOImpl(EntityManager em) {
		super(em);
	}

}
