package interactor;

import model.Cargo;
import model.Funcionario;

public enum CalculoDeSalarioPorCargo {

	DESENVOLVEDOR(Cargo.DESENVOLVEDOR, new DezOuVintePorcento()),
	DBA(Cargo.DBA, new QuinzeOuVintePorcento()),
	TESTER(Cargo.TESTER, new QuinzeOuVintePorcento());
	
	
	private final Cargo cargo;
	private final RegraDeCalculo regra;

	CalculoDeSalarioPorCargo(Cargo cargo, RegraDeCalculo regra){
		this.cargo = cargo;
		this.regra = regra;
	}
	
	public static RegraDeCalculo getRegraDeCalculo(Cargo cargo) {
		for (CalculoDeSalarioPorCargo c : values()) {
			if (c.cargo == cargo) {
				return c.regra;
			}
		}
		throw new RuntimeException("Regra de cálculo não encontrada");
	}

	public Cargo getCargo() {
		return cargo;
	}

	public RegraDeCalculo getRegraDeCalculo() {
		return regra;
	}
	
}
