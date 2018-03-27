package br.com.caelum.financas.test;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JpaUtil;

import javax.persistence.EntityManager;

public class BuscaContaTest {

	public static void main(String[] args) {
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		Conta conta = manager.find(Conta.class, 1);
		
		System.out.println(conta.getTitular());
		
		manager.getTransaction().commit();
		manager.close();
		
		
		
	}
	
}
