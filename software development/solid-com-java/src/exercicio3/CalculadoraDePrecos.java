package exercicio3;

public class CalculadoraDePrecos {
	
	private TabelaDePreco tabelaDePreco;
	private ServicoDeEntrega entrega;
	
	public CalculadoraDePrecos(TabelaDePreco tabelaDePreco, ServicoDeEntrega entrega) {
		this.tabelaDePreco = tabelaDePreco;
		this.entrega = entrega;
	}

	public double calcular(Compra produto) {
		
		double desconto = tabelaDePreco.descontoPara(produto.getValor());
		double frete = entrega.para(produto.getCidade());
		
		return produto.getValor() * (1 - desconto) + frete;
	}
	
}
