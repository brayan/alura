package interactor;

import model.Funcionario;

public class CalculadoraDeSalario {

	public double calcular(Funcionario funcionario) {
		RegraDeCalculo regra = CalculoDeSalarioPorCargo.getRegraDeCalculo(funcionario.getCargo());
		return regra.calcular(funcionario);	
	}

}