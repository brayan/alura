package br.com.sailboat.financeirok.view

import android.view.View
import br.com.sailboat.financeirok.extension.formatarParaBrasileiro
import br.com.sailboat.financeirok.model.Resumo
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView(private val view: View) {

    fun adicionarTotais(transacoes: List<Transacao>) {
        val resumo = Resumo()

        var totalReceita = resumo.calcularReceita(transacoes)
        var totalDespesa = resumo.calcularDespesa(transacoes)
        var total = resumo.calcularTotal(transacoes)

        view.resumo_card_receita.text = totalReceita.formatarParaBrasileiro()
        view.resumo_card_despesa.text = totalDespesa.formatarParaBrasileiro()
        view.resumo_card_total.text = total.formatarParaBrasileiro()
    }

}