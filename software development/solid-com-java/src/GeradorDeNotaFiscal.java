import java.util.List;

public class GeradorDeNotaFiscal {
	
	public static NotaFiscal gerar(Fatura fatura) {
		return gerar(fatura, null);
	}
	
	public static NotaFiscal gerar(Fatura fatura, List<AoGerarNotaFiscal> callbacks) {
		double valor = fatura.getValorMensal();

		NotaFiscal notaFiscal = new NotaFiscal(0, valor, calcularImpostoSimples(valor));

		aoGerarNotaFiscal(callbacks, notaFiscal);

		return notaFiscal;
	}
	

	private static double calcularImpostoSimples(double valor) {
		return valor * 0.06;
	}

	private static void aoGerarNotaFiscal(List<AoGerarNotaFiscal> callbacks, NotaFiscal notaFiscal) {

		if (callbacks != null && !callbacks.isEmpty()) {

			for (AoGerarNotaFiscal callback : callbacks) {
				callback.aoGerarNotaFiscal(notaFiscal);
			}

		}
	}

}
