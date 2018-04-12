package exercicio5;

public class ContaComum {
	
	private ManipuladorDeSaldo manipulador = new ManipuladorDeSaldo();

    public void deposita(double valor) {
        manipulador.deposita(valor);
    }

    public void saca(double valor) {
        manipulador.saca(valor);
    }

    public void rende() {
        manipulador.rende(1.1);
    }

}