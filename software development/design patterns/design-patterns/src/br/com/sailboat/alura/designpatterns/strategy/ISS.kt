package br.com.sailboat.alura.designpatterns

class ISS() : Imposto() {

	private lateinit var imposto: Imposto

	constructor(imposto: Imposto) : this() {
		this.imposto = imposto
	}

	override fun calcular(orcamento: Orcamento) = orcamento.valor * 0.06

}