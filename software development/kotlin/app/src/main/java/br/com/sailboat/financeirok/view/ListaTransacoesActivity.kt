package br.com.sailboat.financeirok.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.dao.TransacaoDAO
import br.com.sailboat.financeirok.model.TipoTransacao
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val dao = TransacaoDAO()

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

        AdicionarTransacaoDialog(this).mostrarDialog(tipoTransacao) {
            dao.adicionar(it)
            atualizarTransacoes()
            lista_transacoes_adiciona_menu.close(true)
        }

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

        listviewTransacoes.setOnCreateContextMenuListener { menu, v, menuInfo ->
            menu.add(Menu.NONE, 1, Menu.NONE, "Remover")

        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            1 -> {
                val adapterMenu = (item.menuInfo as AdapterView.AdapterContextMenuInfo);
                onClickRemover(adapterMenu.position)
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    private fun onClickRemover(position: Int) {
        dao.remover(position)
        atualizarTransacoes()
    }

    private fun onClickTransacao(position: Int) {
        val transacao = dao.transacoes[position]
        AlterarTransacaoDialog(this, transacao, object : AlterarTransacaoDialog.Callback {
            override fun onClickAlterar(transacao: Transacao) {
                dao.alterar(transacao, position)
                atualizarTransacoes()
                lista_transacoes_adiciona_menu.close(true)
            }
        }).mostrarDialog()
    }

    private fun inicializarResumo() {
        val resumoView = ResumoView(this, window.decorView)
        resumoView.atualizarTotais(dao.transacoes);
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
        listviewTransacoes.adapter = ListaTransacoesAdapter(dao.transacoes, this)
    }


}