package br.com.sailboat.alura.designpatterns.chain_of_responsibility

import br.com.sailboat.alura.designpatterns.Orcamento

interface Desconto {
    fun calcularDesconto(orcamento: Orcamento): Double
    fun setProximo(proximo: Desconto)
}