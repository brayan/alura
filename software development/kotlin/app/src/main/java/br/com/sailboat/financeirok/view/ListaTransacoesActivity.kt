package br.com.sailboat.financeirok.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.model.TipoTransacao
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    companion object {

        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ListaTransacoesActivity.javaClass)
            activity.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        inicializarAdapter()
    }

    private fun inicializarAdapter() {
        listviewTransacoes.adapter = ListaTransacoesAdapter(criarTransacoesDeExemplo(), this)
    }

    private fun criarTransacoesDeExemplo(): List<Transacao> {
        val transacao1 = Transacao(BigDecimal("20.50"), "Comida", TipoTransacao.DESPESA)
        val transacao2 = Transacao(
            valor = BigDecimal("100.0"),
            categoria = "Economia",
            tipo = TipoTransacao.RECEITA
        )
        val transacao3 = Transacao(valor = BigDecimal("300.0"), tipo = TipoTransacao.RECEITA)

        val transacoes = listOf(transacao1, transacao2, transacao3)

        return transacoes
    }


}