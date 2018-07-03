package br.com.sailboat.alura.designpatterns

class ICMS() : Imposto() {

	override fun calcular(orcamento: Orcamento) = orcamento.valor * 0.1

}