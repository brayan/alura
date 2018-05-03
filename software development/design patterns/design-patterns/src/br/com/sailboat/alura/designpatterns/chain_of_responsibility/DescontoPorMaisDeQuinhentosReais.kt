package br.com.sailboat.alura.designpatterns.chain_of_responsibility

import br.com.sailboat.alura.designpatterns.Orcamento

class DescontoPorMaisDeQuinhentosReais : Desconto {

    var proximoDesconto: Desconto? = null

    override fun calcularDesconto(orcamento: Orcamento): Double {

        if (orcamento.valor > 500) {
            return orcamento.valor * 0.07
        } else {
            return proximoDesconto?.calcularDesconto(orcamento) ?: 0.1
        }
    }

    override fun setProximo(proximo: Desconto) {
        this.proximoDesconto = proximo
    }
}