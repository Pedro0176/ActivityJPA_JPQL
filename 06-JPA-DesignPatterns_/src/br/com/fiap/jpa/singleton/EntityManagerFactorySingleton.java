package br.com.fiap.jpa.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	//Definir o atributo que armazena a �nica instancia
	private static EntityManagerFactory emf;
	
	//Consturto privado -> n�o � poss�vel fazer new EntityManagerFactorySingleton();
	private EntityManagerFactorySingleton() {}
	
	//M�todo que gerencia a �nica inst�ncia
	public static EntityManagerFactory getInstance(){
		
		//Verifica se � necess�rio instanciar a f�brica
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("oracle");
		}
		
		
		return emf;
	}
	

	
	
	
	

}
