package br.com.alura.aluraviagens.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.DrawableUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;

public class ListaPacotesAdapter extends BaseAdapter {

    private final List<Pacote> pacotes;
    private Context context;

    public ListaPacotesAdapter(Context context, List<Pacote> pacotes) {
        this.context = context;
        this.pacotes = pacotes;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Pacote getItem(int position) {
        return pacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_pacote, parent, false);

        Pacote pacote = pacotes.get(position);
        bindLocal(viewCriada, pacote);
        bindImagem(viewCriada, pacote);
        bindDias(viewCriada, pacote);
        bindPreco(viewCriada, pacote);

        return viewCriada;
    }

    private void bindLocal(View viewCriada, Pacote pacote) {
        TextView tvLocal = viewCriada.findViewById(R.id.item_pacote__local);
        tvLocal.setText(pacote.getLocal());
    }

    private void bindImagem(View viewCriada, Pacote pacote) {
        ImageView ivPacote = viewCriada.findViewById(R.id.item_pacote__iv);
        ivPacote.setImageDrawable(DrawableUtil.getDrawableFromName(context, pacote.getImagem()));
    }

    private void bindDias(View viewCriada, Pacote pacote) {
        TextView dias = viewCriada.findViewById(R.id.item_pacote__tv__dias);
        dias.setText(pacote.getDias() + DiasUtil.formatarDias(pacote.getDias()));
    }

    private void bindPreco(View viewCriada, Pacote pacote) {
        TextView preco = viewCriada.findViewById(R.id.item_pacote__tv__preco);
        preco.setText(MoedaUtil.formatarPrecoParaReal(pacote.getPreco()));
    }

}