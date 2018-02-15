package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;

@Repository
@Transactional
public class ProdutoDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("SELECT DISTINCT(p) FROM Produto p join fetch p.precos", Produto.class).getResultList();	
	}

	public Produto find(Integer id) {
		return manager.createQuery("SELECT DISTINCT(p) FROM Produto p JOIN FETCH p.precos preco WHERE p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
	}
	
	public BigDecimal somarPrecosPorTipoPreco(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = manager.createQuery("SELECT SUM(preco.valor) FROM Produto p "
				+ " JOIN p.precos preco WHERE preco.tipo = :tipoPreco ", BigDecimal.class);
		
		query.setParameter("tipoPreco", tipoPreco);
		
		return query.getSingleResult();
	}
	
}
