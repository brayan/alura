package br.com.caelum.financas.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.model.Categoria;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class MovimentacaoPorCategoriaTest {

	public static void main(String[] args) {
		
		
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		
		String jpql = "SELECT m FROM Movimentacao m INNER JOIN m.categorias c WHERE c = :categoria";
		Query query = manager.createQuery(jpql);
		query.setParameter("categoria", categoria);
		
		List<Movimentacao> listaMovimentacao = query.getResultList();
		
		for (Movimentacao movimentacao : listaMovimentacao) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
			System.out.println();
		}
		 
		manager.getTransaction().commit();
		
		manager.close();
	}
}
