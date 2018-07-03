package br.com.sailboat.financeirok.dao

import br.com.sailboat.financeirok.model.Transacao

class TransacaoDAO {

    val transacoes = Companion.transacoes.toList()

    // péssima prática pra manter as transações na memória
    companion object {
        private val transacoes = mutableListOf<Transacao>()
    }

    fun adicionar(transacao: Transacao) {
        Companion.transacoes.add(transacao)
    }

    fun alterar(transacao: Transacao, posicao: Int) {
        Companion.transacoes[posicao] = transacao
    }

    fun remover(posicao: Int) {
        Companion.transacoes.removeAt(posicao)
    }

}