package br.com.sailboat.alura.designpatterns.strategy

import br.com.sailboat.alura.designpatterns.CalculadorDeImposto
import br.com.sailboat.alura.designpatterns.ICMS
import br.com.sailboat.alura.designpatterns.ISS
import br.com.sailboat.alura.designpatterns.Orcamento

fun main(args: Array<String>) {
	val iss = ISS()
	val icms = ICMS()

	val orcamento = Orcamento(500.0)

	val calculador = CalculadorDeImposto()

	calculador.realizarCalculo(orcamento, iss)
	calculador.realizarCalculo(orcamento, icms)
}