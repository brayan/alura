package br.com.caelum.financas.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.model.Conta;

public class ContaTest {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("Leonardo");
		conta.setAgencia("123");
		conta.setBanco("Caixa Economica");
		conta.setNumero("456");
		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("financas");
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(conta);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
		
	}
	
}
