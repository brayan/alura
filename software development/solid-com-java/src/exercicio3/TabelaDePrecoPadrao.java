package exercicio3;

public class TabelaDePrecoPadrao implements TabelaDePreco {

	@Override
	public double descontoPara(double valor) {
		
		if (valor > 5000) { 
			return 0.05;
		}
		
		if (valor > 1000) {
			return 0.05;
		}
		
		return 0;
	}
}