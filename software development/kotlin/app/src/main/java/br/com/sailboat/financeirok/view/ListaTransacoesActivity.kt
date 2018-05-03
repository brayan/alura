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
        inicializarLista()
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
                    transacoes.add(transacao)
                    atualizarTransacoes()
                    lista_transacoes_adiciona_menu.close(true)
                }

            }
        ).mostrarDialog()
    }

    private fun atualizarTransacoes() {
        inicializarResumo()
        inicializarAdapter()
    }

    private fun inicializarLista() {
        inicializarAdapter()
        listviewTransacoes.setOnItemClickListener { parent, view, position, id ->
            onClickTransacao(position)
        }
    }

    private fun onClickTransacao(position: Int) {
        val transacao = transacoes[position]
        AlterarTransacaoDialog(this, transacao, object: AlterarTransacaoDialog.Callback{
            override fun onClickAlterar(transacao: Transacao) {
                transacoes[position] = transacao
                atualizarTransacoes()
                lista_transacoes_adiciona_menu.close(true)
            }
        }).mostrarDialog()
    }

    private fun inicializarResumo() {
        val resumoView = ResumoView(this, window.decorView)
        resumoView.atualizarTotais(transacoes);
    }

    private fun inicializarFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            onClickAdicionarReceita()
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            onClickAdicionarDespesa()
        }
    }

    private fun inicializarAdapter() {
        listviewTransacoes.adapter = ListaTransacoesAdapter(transacoes, this)
    }


}