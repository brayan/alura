package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("financas");
	
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
