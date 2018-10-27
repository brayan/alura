package br.com.alura.aluraviagens.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.dao.PacoteDAO;
import br.com.alura.aluraviagens.ui.adapter.ListaPacotesAdapter;

public class ListaPacotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);

        setTitle(R.string.title_pacotes);

        configurarLista();
    }

    private void configurarLista() {
        ListView listaDePacotes = findViewById(R.id.lista_pacotes__lv);
        listaDePacotes.setAdapter(new ListaPacotesAdapter(this, new PacoteDAO().lista()));
    }

}
