package br.com.sailboat.financeirok.model

import java.math.BigDecimal

class Resumo {

    fun calcularReceita(transacoes: List<Transacao>): BigDecimal {
        var totalReceita = BigDecimal.ZERO

        transacoes.filter { it.tipo == TipoTransacao.RECEITA }
            .forEach { totalReceita = totalReceita.plus(it.valor) }

        return totalReceita
    }

    fun calcularDespesa(transacoes: List<Transacao>): BigDecimal {
        var totalDespesa = BigDecimal.ZERO

        transacoes.filter { it.tipo == TipoTransacao.DESPESA }
            .forEach { totalDespesa = totalDespesa.plus(it.valor) }

        return totalDespesa
    }

    fun calcularTotal(transacoes: List<Transacao>): BigDecimal {
        var total = BigDecimal.ZERO

        transacoes.forEach { total = total.plus(it.valor) }

        return total
    }

}