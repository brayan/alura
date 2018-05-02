package br.com.sailboat.alura.designpatterns

class ISS : Imposto {

	override fun calcular(orcamento: Orcamento) = orcamento.valor * 0.06

}