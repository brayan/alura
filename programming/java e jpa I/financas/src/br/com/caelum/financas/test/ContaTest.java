package br.com.caelum.financas.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JpaUtil;

public class ContaTest {

	public static void main(String[] args) {
		// conta is transient state
		Conta conta = new Conta();
		conta.setTitular("Leonardo");
		conta.setAgencia("123");
		conta.setBanco("Caixa Economica");
		conta.setNumero("456");
		
		
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		// conta is managed state
		manager.persist(conta);
		manager.getTransaction().commit();
		
		manager.close();
		
		// conta is detached state
		
		// use the merge method to transform a detached entity into managed
		
	}
	
}
