package br.com.alura.aluraviagens.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import br.com.alura.aluraviagens.R;

public class PagamentoActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, PagamentoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
    }

}
