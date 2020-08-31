package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Reserva;

public interface ReservaDAO extends GenericDAO<Reserva,Integer>{
	
	long contaPorCliente(int idCliente); 
	
	List<Reserva> buscarPorDescricaoPacote(String descricao);
	
	double calcularMediaDiasPorCliente(String nome);

}
