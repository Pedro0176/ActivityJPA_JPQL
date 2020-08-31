package br.com.fiap.jpa.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.jpa.dao.LoginDAO;
import br.com.fiap.jpa.entity.Login;

public class LoginDAOImpl extends GenericDAOImpl<Login,Integer> implements LoginDAO {

	public LoginDAOImpl(EntityManager em) {
		super(em);
	}

}
