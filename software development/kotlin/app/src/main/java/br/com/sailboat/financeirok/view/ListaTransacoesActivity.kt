package br.com.sailboat.financeirok.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.model.TipoTransacao
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        inicializarResumo()
        inicializarAdapter()

        lista_transacoes_adiciona_receita.setOnClickListener {
            onClickAdicionarReceita()
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            onClickAdicionarDespesa()
        }
    }

    private fun onClickAdicionarReceita() {
        AdicionarTransacaoDialog(this,
            object : AdicionarTransacaoDialog.Callback {

                override fun onClickAdicionar(transacao: Transacao) {
                    atualizarTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }

            }, TipoTransacao.RECEITA,
            R.string.adiciona_receita,
            R.array.categorias_de_receita
        ).mostrarDialog()
    }

    private fun onClickAdicionarDespesa() {
        AdicionarTransacaoDialog(this,
            object : AdicionarTransacaoDialog.Callback {

                override fun onClickAdicionar(transacao: Transacao) {
                    atualizarTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }

            }, TipoTransacao.DESPESA,
            R.string.adiciona_despesa,
            R.array.categorias_de_despesa
        ).mostrarDialog()
    }

    private fun atualizarTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        inicializarAdapter()
        inicializarResumo()
    }

    private fun inicializarResumo() {
        val resumoView = ResumoView(this, window.decorView)
        resumoView.atualizarTotais(transacoes);
    }

    private fun inicializarAdapter() {
        listviewTransacoes.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun criarTransacoesDeExemplo(): List<Transacao> {
        val transacao1 = Transacao(BigDecimal("400.0"), "Comida", TipoTransacao.DESPESA)
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