package br.com.sailboat.alura.designpatterns.chain_of_responsibility

import br.com.sailboat.alura.designpatterns.Orcamento

class SemDesconto : Desconto{

    override fun calcularDesconto(orcamento: Orcamento): Double {
        return 0.0
    }

    override fun setProximo(proximo: Desconto) {
    }
}