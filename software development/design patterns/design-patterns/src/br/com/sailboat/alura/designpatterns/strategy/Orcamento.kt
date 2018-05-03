package br.com.sailboat.alura.designpatterns

import br.com.sailboat.alura.designpatterns.chain_of_responsibility.Item

data class Orcamento(val valor: Double, val itens: MutableList<Item>) {

    fun addItem(item: Item) {
        itens.add(item)
    }

}