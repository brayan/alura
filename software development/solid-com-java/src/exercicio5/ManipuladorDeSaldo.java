package exercicio5;

public class ManipuladorDeSaldo {

	private double saldo;

	public void deposita(double valor) {
		this.saldo += valor;
	}

	public void saca(double valor) {
		if (valor <= this.saldo) {
			this.saldo -= valor;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void rende(double rende) {
		this.saldo += this.saldo * rende;
	}
	
}
