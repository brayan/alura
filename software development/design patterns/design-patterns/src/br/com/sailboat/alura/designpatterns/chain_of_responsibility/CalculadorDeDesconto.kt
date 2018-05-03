package br.com.sailboat.alura.designpatterns.chain_of_responsibility

import br.com.sailboat.alura.designpatterns.Orcamento

class CalculadorDeDesconto {

    fun calcular(orcamento: Orcamento): Double {
        val d1 = DescontoPorCincoItens()
        val d2 = DescontoPorMaisDeQuinhentosReais()
        val d3 = SemDesconto()

        d1.setProximo(d2)
        d2.setProximo(d3)

        return d1.calcularDesconto(orcamento);
    }
}