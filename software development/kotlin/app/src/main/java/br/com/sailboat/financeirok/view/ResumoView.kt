package br.com.sailboat.financeirok.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.extension.formatarParaBrasileiro
import br.com.sailboat.financeirok.model.Resumo
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val context: Context, private val view: View) {

    fun atualizarTotais(transacoes: List<Transacao>) {
        val resumo = Resumo()

        var totalReceita = resumo.calcularReceita(transacoes)
        var totalDespesa = resumo.calcularDespesa(transacoes)
        var total = resumo.calcularTotal(transacoes)

        view.resumo_card_receita.text = totalReceita.formatarParaBrasileiro()
        view.resumo_card_despesa.text = totalDespesa.formatarParaBrasileiro()
        view.resumo_card_total.text = total.formatarParaBrasileiro()

        inicializarCores(transacoes)
    }

    fun inicializarCores(transacoes: List<Transacao>) {
        val resumo = Resumo()

        var total = resumo.calcularTotal(transacoes)

        view.resumo_card_receita.setTextColor(ContextCompat.getColor(context, R.color.receita))
        view.resumo_card_despesa.setTextColor(ContextCompat.getColor(context, R.color.despesa))


        if (total.compareTo(BigDecimal.ZERO) >= 0) {
            view.resumo_card_total.setTextColor(ContextCompat.getColor(context, R.color.receita))
        } else {
            view.resumo_card_total.setTextColor(ContextCompat.getColor(context, R.color.despesa))
        }
    }

}