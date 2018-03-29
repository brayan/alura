package br.com.caelum.financas.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Categoria;
import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class MovimentacoesComCategoriaTest {

	public static void main(String[] args) {
		Categoria categoria1 = new Categoria();
		categoria1.setNome("Viagem");
		
		Categoria categoria2 = new Categoria();
		categoria2.setNome("Negócios");
		
		Conta conta = new Conta();
		conta.setId(2);
		
		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(Calendar.getInstance());
		movimentacao1.setDescricao("Viagem à SP");
		movimentacao1.setTipo(TipoMovimentacao.SAIDA);
		movimentacao1.setValor(new BigDecimal("100.0"));
		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));
		
		movimentacao1.setConta(conta);
		
		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setData(Calendar.getInstance());
		movimentacao2.setDescricao("Viagem à RJ");
		movimentacao2.setTipo(TipoMovimentacao.SAIDA);
		movimentacao2.setValor(new BigDecimal("300.0"));
		movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));

		movimentacao2.setConta(conta);
		
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		manager.persist(categoria1);
		manager.persist(categoria2);

		manager.persist(movimentacao1);
		manager.persist(movimentacao2);
		
		manager.getTransaction().commit();
		
		manager.close();
	
	}
}
