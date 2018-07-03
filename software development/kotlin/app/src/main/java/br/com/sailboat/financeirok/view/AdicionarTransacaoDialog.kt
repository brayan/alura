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

class AdicionarTransacaoDialog(val context: Context) {

    private val viewCriada = View.inflate(context, R.layout.form_transacao, null)

    fun mostrarDialog(tipoTransacao: TipoTransacao, delagate: (transacao: Transacao) -> Unit) {
        configurarCampoData()
        configurarCampoCategorias(tipoTransacao)
        configurarFormulario(tipoTransacao, delagate)
    }

    private fun configurarFormulario(
        tipoTransacao: TipoTransacao,
        delagate: (transacao: Transacao) -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle(tipoTransacao.tituloAdicionar)
            .setView(viewCriada)
            .setPositiveButton("Adicionar", { dialog, which ->

                val valorEmTexto = viewCriada.tvValor.text.toString()
                val dataEmTexto = viewCriada.tvData.text.toString()
                val categoriaEmTexto = viewCriada.spCategoria.selectedItem.toString()

                val valor = converterCampoValor(valorEmTexto)

                val transacao = Transacao(
                    tipo = tipoTransacao,
                    valor = valor,
                    data = dataEmTexto.convertFromShortDateToCalender(),
                    categoria = categoriaEmTexto
                )

                delagate(transacao)

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

    private fun configurarCampoCategorias(tipoTransacao: TipoTransacao) {
        val adapter = ArrayAdapter.createFromResource(
            context,
            tipoTransacao.categorias,
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

}