package br.com.alura.leilao.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Lance implements Serializable, Comparable<Lance> {

    private final Usuario usuario;
    private final double valor;

    public Lance(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public int compareTo(@NonNull Lance o) {
        if (this.valor > o.valor) {
            return -1;
        }

        if (this.valor < o.valor) {
            return 1;
        }

        return 0;
    }

}
