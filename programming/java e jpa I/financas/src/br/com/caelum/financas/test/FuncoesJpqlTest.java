package br.com.caelum.financas.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.type.descriptor.java.BigIntegerTypeDescriptor;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.Movimentacao;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class FuncoesJpqlTest {

	public static void main(String[] args) {
		
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		MovimentacaoDao dao = new MovimentacaoDao(manager);
		List<Double> medias = dao.getMedias(TipoMovimentacao.SAIDA, conta);
		
		System.out.println("A média do dia 26 é: " + medias.get(0));
		System.out.println("A média do dia 27 é: " + medias.get(1));
		 
		manager.getTransaction().commit();
		manager.close();
	}
	
}
