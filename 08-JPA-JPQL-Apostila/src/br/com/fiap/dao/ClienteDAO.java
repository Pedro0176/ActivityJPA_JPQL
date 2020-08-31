package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente,Integer> {
	
	//List<Cliente> listar();
	
	List<Cliente> buscarPorNome(String nome);
	
	List<Cliente> buscarPorNome(String nome, int maximo, int primeiraPosicao);
	
	List<Cliente> buscarPorEstado(String estado);
	
	List<Cliente> buscarPorDiasReserva(int dias);
	
	List<Cliente> buscarPorNomeEstado(String nome, String estado);
	
	List<Cliente> buscar(String nome, String cidade);
	
	List<Cliente> buscarPorEstados(List<String> estados);
	
	long qtdClientePorEstado(String estado);
	


}

