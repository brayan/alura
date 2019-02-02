package br.com.alura.aluraviagens.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

import java.math.BigDecimal;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do Pacote";

    private ImageView ivPacote;
    private TextView tvCidade;
    private TextView tvDias;
    private TextView tvPreco;
    private TextView tvData;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ResumoPacoteActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);
        setUpViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Pacote pacoteSaoPaulo = new Pacote("São Paulo", "sao_paulo_sp", 2, new BigDecimal("243.93"));
        bindPacote(pacoteSaoPaulo);

        PagamentoActivity.start(this);
    }

    private void setUpViews() {
        ivPacote = findViewById(R.id.ivPacote);
        tvCidade = findViewById(R.id.tvCidade);
        tvDias = findViewById(R.id.tvDias);
        tvPreco = findViewById(R.id.tvPreco);
        tvData = findViewById(R.id.tvData);
        setTitle(TITULO_APPBAR);
    }

    private void bindPacote(Pacote pacote) {
        ivPacote.setImageDrawable(ResourceUtil.devolveDrawable(this, pacote.getImagem()));
        tvCidade.setText(pacote.getLocal());
        tvDias.setText(DiasUtil.formataEmTexto(pacote.getDias()));
        tvPreco.setText(MoedaUtil.formataParaBrasileiro(pacote.getPreco()));
        tvData.setText(DataUtil.formatarDataDaViagem(pacote.getDias()));
    }

}
