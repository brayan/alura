package br.com.caelum.financas.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class JpqlTest {

	public static void main(String[] args) {
		
		
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
//		String jpql = "SELECT m FROM Movimentacao m WHERE m.conta.id = 2";
		String jpql = "SELECT m FROM Movimentacao m WHERE m.conta = :conta AND m.tipo = :tipo ORDER BY m.valor DESC";
		Query query = manager.createQuery(jpql);
		query.setParameter("conta", conta);
		query.setParameter("tipo", TipoMovimentacao.SAIDA);
		
		List<Movimentacao> listaMovimentacao = query.getResultList();
		
		for (Movimentacao movimentacao : listaMovimentacao) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
			System.out.println();
		}
		 
//		manager.persist(cliente);
		
		manager.getTransaction().commit();
		
		manager.close();
	}
}
