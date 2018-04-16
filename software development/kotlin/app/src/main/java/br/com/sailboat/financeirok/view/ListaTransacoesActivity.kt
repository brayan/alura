package br.com.sailboat.financeirok.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.extension.toShortDateBrazil
import br.com.sailboat.financeirok.model.TipoTransacao
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

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
    }

    private fun onClickAdicionarReceita() {
        val viewCriada = View.inflate(this, R.layout.form_transacao, null)

        val today = Calendar.getInstance()

        viewCriada.form_transacao_data.setText(today.toShortDateBrazil())

        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(year, month, dayOfMonth)
                    viewCriada.form_transacao_data.setText(dataSelecionada.toShortDateBrazil())
                }, 2018, 3, 13
            ).show()
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.categorias_de_receita,
            android.R.layout.simple_spinner_dropdown_item
        )

        viewCriada.form_transacao_categoria.adapter = adapter

        AlertDialog.Builder(this)
            .setTitle(R.string.adiciona_receita)
            .setView(viewCriada)
            .setPositiveButton("Adicionar", { dialog, which ->

                val valorEmTexto = viewCriada.form_transacao_valor.text.toString()
                val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()


                val valor = try {
                    BigDecimal(valorEmTexto)
                } catch (exception: NumberFormatException) {
                    Toast.makeText(this, "Falha na conversão de valor", Toast.LENGTH_LONG).show()
                    BigDecimal.ZERO
                }

                val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
                val dataConvertida = formatoBrasileiro.parse(dataEmTexto)

                val data = Calendar.getInstance()
                data.time = dataConvertida

                val transacao = Transacao(
                    tipo = TipoTransacao.RECEITA,
                    valor = valor,
                    data = data,
                    categoria = categoriaEmTexto
                )

                atualizarTransacoes(transacao)

                lista_transacoes_adiciona_menu.close(true)


            })
            .setNegativeButton("Cancelar", null)
            .show()
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