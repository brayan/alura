package br.com.sailboat.financeirok.view

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.extension.toShortDateBrazil
import br.com.sailboat.financeirok.model.TipoTransacao
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class AdicionarTransacaoDialog(val context: Context,
                               val callback: AdicionarTransacaoDialog.Callback,
                               val tipoTransacao: TipoTransacao,
                               val titulo: Int,
                               val categorias: Int) {

    private val viewCriada = View.inflate(context, R.layout.form_transacao, null)

    fun mostrarDialog() {
        configurarCampoData()
        configurarCampoCategorias()
        configurarFormulario()
    }

    private fun configurarFormulario() {
        AlertDialog.Builder(context)
            .setTitle(titulo)
            .setView(viewCriada)
            .setPositiveButton("Adicionar", { dialog, which ->

                val valorEmTexto = viewCriada.form_transacao_valor.text.toString()
                val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()

                val valor = converterCampoValor(valorEmTexto)

                val transacao = Transacao(
                    tipo = tipoTransacao,
                    valor = valor,
                    data = dataEmTexto.convertFromShortDateToCalender(),
                    categoria = categoriaEmTexto
                )

                callback.onClickAdicionar(transacao)

            })
            .setNegativeButton("Cancelar", null)
            .show()
    }

    fun String.convertFromShortDateToCalender(): Calendar {
        val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
        val dataConvertida = formatoBrasileiro.parse(this)

        val data = Calendar.getInstance()
        data.time = dataConvertida

        return data
    }

    private fun converterCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversão de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configurarCampoCategorias() {
        val adapter = ArrayAdapter.createFromResource(
            context,
            categorias,
            android.R.layout.simple_spinner_dropdown_item
        )

        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configurarCampoData() {
        val today = Calendar.getInstance()

        viewCriada.form_transacao_data.setText(today.toShortDateBrazil())

        val ano = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(year, month, dayOfMonth)
                    viewCriada.form_transacao_data.setText(dataSelecionada.toShortDateBrazil())
                }, ano, month, day
            ).show()
        }
    }

    interface Callback {
        fun onClickAdicionar(transacao: Transacao)
    }
}