package br.com.fiap.jpa.dao;

import br.com.fiap.jpa.exception.FailledCommitException;
import br.com.fiap.jpa.exception.KeyNotFoundException;

public interface GenericDAO<T,K> {

	
	void creat(T entity);
	T read (K key) throws  KeyNotFoundException;
	void update(T entity);
	void delete(K key) throws KeyNotFoundException;
	void commit() throws FailledCommitException;
}
