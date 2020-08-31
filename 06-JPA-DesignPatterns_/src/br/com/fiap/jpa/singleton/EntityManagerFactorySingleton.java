package br.com.fiap.jpa.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	//Definir o atributo que armazena a única instancia
	private static EntityManagerFactory emf;
	
	//Consturto privado -> não é possível fazer new EntityManagerFactorySingleton();
	private EntityManagerFactorySingleton() {}
	
	//Método que gerencia a única instância
	public static EntityManagerFactory getInstance(){
		
		//Verifica se é necessário instanciar a fábrica
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("oracle");
		}
		
		
		return emf;
	}
	

	
	
	
	

}
