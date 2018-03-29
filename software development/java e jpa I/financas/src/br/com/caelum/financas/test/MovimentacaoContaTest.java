package br.com.caelum.financas.test;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class MovimentacaoContaTest {

	public static void main(String[] args) {
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		Movimentacao movimentacao = manager.find(Movimentacao.class, 3);
		Conta conta = movimentacao.getConta();
		
		System.out.println("Titular: " + conta.getTitular());
		System.out.println("Quantidade: " + conta.getMovimentacoes().size());
		
		 
		manager.getTransaction().commit();
		
		manager.close();
	}
	
}
