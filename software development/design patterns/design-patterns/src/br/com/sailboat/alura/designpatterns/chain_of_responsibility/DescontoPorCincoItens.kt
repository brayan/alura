package br.com.sailboat.alura.designpatterns.chain_of_responsibility

import br.com.sailboat.alura.designpatterns.Orcamento

class DescontoPorCincoItens : Desconto {

    var proximoDesconto: Desconto? = null

    override fun calcularDesconto(orcamento: Orcamento): Double {

        if (orcamento.itens.size > 5) {
            return orcamento.valor * 0.1
        }

        return .0
    }

    override fun setProximo(proximo: Desconto) {
        this.proximoDesconto = proximo
    }
}