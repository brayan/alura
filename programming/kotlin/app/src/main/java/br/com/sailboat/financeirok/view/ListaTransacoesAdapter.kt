package br.com.sailboat.financeirok.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.extension.formatarParaBrasileiro
import br.com.sailboat.financeirok.extension.toShortDateBrazil
import br.com.sailboat.financeirok.model.TipoTransacao
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>, private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]

        bindValor(transacao, view)
        bindIcone(transacao, view)
        bindCategoria(transacao, view)
        bindData(transacao, view)

        return view
    }

    private fun bindData(transacao: Transacao, view: View) {
        view.transacao_data.text = transacao.data.toShortDateBrazil()
    }

    private fun bindCategoria(transacao: Transacao, view: View) {
        view.transacao_categoria.text = transacao.categoria
    }

    private fun bindIcone(transacao: Transacao, view: View) {
        val icone: Int = when (transacao.tipo) {
            TipoTransacao.RECEITA -> R.drawable.icone_transacao_item_receita
            TipoTransacao.DESPESA -> R.drawable.icone_transacao_item_despesa
        }

        view.transacao_icone.setImageResource(icone)
    }

    private fun bindValor(transacao: Transacao, view: View) {
        val cor: Int = when (transacao.tipo) {
            TipoTransacao.RECEITA -> R.color.receita
            TipoTransacao.DESPESA -> R.color.despesa
        }

        view.transacao_valor.setTextColor(ContextCompat.getColor(context, cor))
        view.transacao_valor.text = transacao.valor.formatarParaBrasileiro()
    }

    // Any é a object to Kotlin
    override fun getItem(position: Int) = transacoes[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount() = transacoes.size

}