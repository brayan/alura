package br.com.sailboat.financeirok.view

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.sailboat.financeirok.R
import br.com.sailboat.financeirok.extension.toShortDateBrazil
import br.com.sailboat.financeirok.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class AlterarTransacaoDialog(val context: Context,
                             val transacao: Transacao,
                             val callback: AlterarTransacaoDialog.Callback) {

    private val viewCriada = View.inflate(context, R.layout.form_transacao, null)

    fun mostrarDialog() {
        configurarCampoData()
        configurarCampoCategorias()
        configurarFormulario()

        viewCriada.tvValor.setText(transacao.valor.toString())
        viewCriada.tvData.setText(transacao.data.toShortDateBrazil())
        val categorias = context.resources.getStringArray(transacao.tipo.categorias)
        val posicaoCategoria = categorias.indexOf(transacao.categoria)
        viewCriada.spCategoria.setSelection(posicaoCategoria, true);
    }

    private fun configurarFormulario() {
        AlertDialog.Builder(context)
            .setTitle(transacao.tipo.tituloAlterar)
            .setView(viewCriada)
            .setPositiveButton("Alterar", { dialog, which ->
                val valorEmTexto = viewCriada.tvValor.text.toString()
                val dataEmTexto = viewCriada.tvData.text.toString()
                val categoriaEmTexto = viewCriada.spCategoria.selectedItem.toString()

                val valor = converterCampoValor(valorEmTexto)

                val transacaoRetorno = Transacao(
                    tipo = transacao.tipo,
                    valor = valor,
                    data = dataEmTexto.convertFromShortDateToCalender(),
                    categoria = categoriaEmTexto
                )

                callback.onClickAlterar(transacaoRetorno)
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
            transacao.tipo.categorias,
            android.R.layout.simple_spinner_dropdown_item
        )

        viewCriada.spCategoria.adapter = adapter
    }

    private fun configurarCampoData() {
        val today = Calendar.getInstance()

        viewCriada.tvData.setText(today.toShortDateBrazil())

        val ano = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        viewCriada.tvData.setOnClickListener {
            DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(year, month, dayOfMonth)
                    viewCriada.tvData.setText(dataSelecionada.toShortDateBrazil())
                }, ano, month, day
            ).show()
        }
    }

    interface Callback {
        fun onClickAlterar(transacao: Transacao)
    }
}