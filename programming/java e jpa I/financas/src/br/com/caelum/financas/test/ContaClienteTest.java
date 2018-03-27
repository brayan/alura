package br.com.caelum.financas.test;

import javax.persistence.EntityManager;

import br.com.caelum.financas.model.Cliente;
import br.com.caelum.financas.model.Conta;
import br.com.caelum.financas.util.JpaUtil;

public class ContaClienteTest {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Leonardo");
		cliente.setEndereco("Rua Fulano, 123");
		cliente.setProfissao("Professor");
		
		Conta conta = new Conta();
		conta.setId(2);
		
		cliente.setConta(conta);
		
		EntityManager manager = new JpaUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		manager.persist(cliente);
		
		manager.getTransaction().commit();
		
		manager.close();
	}
}