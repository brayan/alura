package br.com.sailboat.financeirok.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.model.TipoTransacao
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        inicializarResumo()
        inicializarAdapter()
        inicializarFab()
    }

    private fun onClickAdicionarReceita() {
        mostrarDialogTransacao(TipoTransacao.RECEITA)
    }

    private fun onClickAdicionarDespesa() {
        mostrarDialogTransacao(TipoTransacao.DESPESA)
    }

    private fun mostrarDialogTransacao(tipoTransacao: TipoTransacao) {
        AdicionarTransacaoDialog(this,
            tipoTransacao,
            object : AdicionarTransacaoDialog.Callback {

                override fun onClickAdicionar(transacao: Transacao) {
                    atualizarTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }

            }
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

    private fun inicializarFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            onClickAdicionarReceita()
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            onClickAdicionarDespesa()
        }
    }

}