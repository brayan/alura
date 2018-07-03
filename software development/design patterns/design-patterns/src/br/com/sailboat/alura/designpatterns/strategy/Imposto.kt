package br.com.sailboat.alura.designpatterns

public abstract class Imposto() {

	private lateinit var imposto: Imposto

	constructor(imposto: Imposto) : this() {
		this.imposto = imposto
	}

	abstract fun calcular(orcamento: Orcamento): Double
}