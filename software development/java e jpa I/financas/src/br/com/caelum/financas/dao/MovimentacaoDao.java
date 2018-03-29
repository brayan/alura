package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.model.TipoMovimentacao;
import br.com.caelum.financas.util.JpaUtil;

public class MovimentacaoDao {
	
	private EntityManager manager;
	
	public MovimentacaoDao(EntityManager manager) {
		this.manager = manager;
	}

	public List<Double> getMedias(TipoMovimentacao tipoMovimentacao, Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT AVG(m.valor) FROM Movimentacao m ");
		sb.append(" WHERE m.conta = :conta AND m.tipo = :tipo ");
		sb.append(" GROUP BY m.data ");
		
//		Query query = manager.createQuery(sb.toString());
		TypedQuery<Double> query = manager.createQuery(sb.toString(), Double.class);
		query.setParameter("conta", conta);
		query.setParameter("tipo", tipoMovimentacao);
		
		return query.getResultList();
	}
}
