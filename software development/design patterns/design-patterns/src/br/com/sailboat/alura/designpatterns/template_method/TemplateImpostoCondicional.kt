package br.com.sailboat.alura.designpatterns.template_method

import br.com.sailboat.alura.designpatterns.Imposto
import br.com.sailboat.alura.designpatterns.Orcamento

abstract class TemplateImpostoCondicional : Imposto {

    override fun calcular(orcamento: Orcamento): Double {

        if (deveUsarMaximaTaxacao(orcamento)) {
            return maximaTaxacao(orcamento)
        } else {
            return minimaTaxacao(orcamento)
        }
    }

    abstract fun minimaTaxacao(orcamento: Orcamento): Double

    abstract fun maximaTaxacao(orcamento: Orcamento): Double

    abstract fun deveUsarMaximaTaxacao(orcamento: Orcamento): Boolean

}