package br.com.sailboat.alura.designpatterns.template_method

import br.com.sailboat.alura.designpatterns.Orcamento

class ICPP : TemplateImpostoCondicional() {

    override fun minimaTaxacao(orcamento: Orcamento): Double {
        return orcamento.valor * 0.05
    }

    override fun maximaTaxacao(orcamento: Orcamento): Double {
        return orcamento.valor * 0.07
    }

    override fun deveUsarMaximaTaxacao(orcamento: Orcamento): Boolean {
        return orcamento.valor > 500
    }

    override fun calcular(orcamento: Orcamento): Double {
        if (orcamento.valor > 500) {
            return orcamento.valor * 0.07
        }
        return orcamento.valor * 0.05
    }

}