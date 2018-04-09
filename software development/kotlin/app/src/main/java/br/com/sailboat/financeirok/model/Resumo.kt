package br.com.sailboat.financeirok.model

import java.math.BigDecimal

class Resumo {

    fun calcularReceita(transacoes: List<Transacao>): BigDecimal {
        return calcularTransacao(transacoes, TipoTransacao.RECEITA)
    }

    fun calcularDespesa(transacoes: List<Transacao>): BigDecimal {
        return calcularTransacao(transacoes, TipoTransacao.DESPESA)
    }

    fun calcularTotal(transacoes: List<Transacao>): BigDecimal {
        val receita = calcularReceita(transacoes)
        val despesa = calcularDespesa(transacoes)

        return receita - despesa
    }

    private fun calcularTransacao(transacoes: List<Transacao>, tipoTransacao: TipoTransacao) : BigDecimal {
        val totalTransacao = transacoes
            .filter { it.tipo == tipoTransacao }
            .sumByDouble { it.valor.toDouble() }

        return BigDecimal(totalTransacao)
    }

}