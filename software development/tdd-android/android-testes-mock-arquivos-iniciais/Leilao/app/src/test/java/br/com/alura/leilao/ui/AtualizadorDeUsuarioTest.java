package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;

    @Mock
    private ListaUsuarioAdapter adapter;

    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deveAtualizarListaDeUsuariosQuandoSalvarUsuario() {
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(dao, adapter, recyclerView);

        when(dao.salva(new Usuario("Brayan"))).thenReturn(new Usuario(1, "Brayan"));
        when(adapter.getItemCount()).thenReturn(1);

        atualizador.salva(new Usuario("Brayan"));

        verify(dao).salva(new Usuario("Brayan"));
        verify(adapter).adiciona(new Usuario(1, "Brayan"));
        verify(recyclerView).smoothScrollToPosition(0);
    }

}