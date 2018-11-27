package br.com.alura.leilao.ui.recyclerview.adapter;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Leilao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoAdapterTest {

    @Mock
    private Context context;

    @Spy
    private ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(context);

    @Test
    public void deveAtualizarListaDeLeiloesQuandoReceberListaDeLeiloes() {
        doNothing().when(adapter).atualizarListaLeiloes();

        adapter.atualiza(new ArrayList(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computador")
        )));

        int quantidadeLeiloes = adapter.getItemCount();
        verify(adapter).atualizarListaLeiloes();

        assertThat(quantidadeLeiloes, is(2));
    }

}