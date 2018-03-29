package br.com.caelum.financas.test;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class JpaRelacionamentoTest {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setAgencia("0102");
		conta.setBanco("Itau");
		conta.setNumero("1234");
		conta.setTitular("Leonardo");
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("200.0"));
		
		movimentacao.setConta(conta);
		
		EntityManager manager = new JpaUtil().getEntityManager();

		manager.getTransaction().begin();
		
		manager.persist(conta);
		manager.persist(movimentacao);
		
		manager.getTransaction().commit();

		manager.close();
	}
}
