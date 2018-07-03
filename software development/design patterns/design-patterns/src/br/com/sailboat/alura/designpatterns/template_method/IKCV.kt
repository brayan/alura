package br.com.sailboat.alura.designpatterns.template_method

import br.com.sailboat.alura.designpatterns.Orcamento

class IKCV : TemplateImpostoCondicional() {

    override fun minimaTaxacao(orcamento: Orcamento): Double {
        return orcamento.valor * 0.06
    }

    override fun maximaTaxacao(orcamento: Orcamento): Double {
        return orcamento.valor * 0.10
    }

    override fun deveUsarMaximaTaxacao(orcamento: Orcamento): Boolean {
        return (orcamento.valor > 500 && possuiItemMaiorQue100Reais(orcamento))
    }

    private fun possuiItemMaiorQue100Reais(orcamento: Orcamento): Boolean {
        orcamento.itens.forEach {
            if (it.valor > 100) {
                return true
            }
        }
        return false
    }

}