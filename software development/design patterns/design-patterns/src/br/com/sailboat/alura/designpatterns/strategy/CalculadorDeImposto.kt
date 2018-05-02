package br.com.sailboat.alura.designpatterns

class CalculadorDeImposto {
	
	fun realizarCalculo(orcamento: Orcamento, imposto: Imposto) {
		println(imposto.calcular(orcamento))
	}
	
}