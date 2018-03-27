package br.com.sailboat.financeirok.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toShortDateBrazil(): String {
    return SimpleDateFormat("dd/MM/yyyy").format(this.time)
}