package br.com.fiap.jpa.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.jpa.dao.PostDAO;
import br.com.fiap.jpa.entity.Post;

public class PostDAOImpl extends GenericDAOImpl<Post, Integer> implements PostDAO{

	public PostDAOImpl(EntityManager em) {
		super(em);
	}
	
	

}
