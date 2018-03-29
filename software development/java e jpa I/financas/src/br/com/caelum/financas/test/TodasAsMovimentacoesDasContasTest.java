package br.com.caelum.financas.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class TodasAsMovimentacoesDasContasTest {
	
	public static void main(String[] args) {
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT DISTINCT c FROM Conta c ");
		sb.append(" LEFT JOIN FETCH c.movimentacoes ");
		sb.append(" ORDER BY c.titular ");
		
		Query query = manager.createQuery(sb.toString());
		
		List<Conta> todasAsContas = query.getResultList();

		for (Conta conta : todasAsContas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentações: ");
			System.out.println(conta.getMovimentacoes());
		}
		
		 
		manager.getTransaction().commit();
		
		manager.close();
	}
	
}
