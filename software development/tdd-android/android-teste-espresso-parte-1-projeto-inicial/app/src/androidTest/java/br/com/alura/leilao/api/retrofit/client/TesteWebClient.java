package br.com.alura.leilao.api.retrofit.client;

import java.io.IOException;

import br.com.alura.leilao.api.retrofit.TesteRetrofitInicializador;
import br.com.alura.leilao.api.retrofit.service.TesteService;
import br.com.alura.leilao.model.Leilao;
import retrofit2.Call;
import retrofit2.Response;

public class TesteWebClient extends WebClient {

    private final TesteService service;

    public TesteWebClient() {
        service = new TesteRetrofitInicializador().getTesteService();
    }

    public Leilao salvar(Leilao leilao) throws IOException {
        Call<Leilao> call = service.salvar(leilao);
        Response<Leilao> response = call.execute();
        if (temDados(response)) {
            return response.body();
        }
        return null;
    }

    public boolean limparBancoDeDados() throws IOException {
        Call<Void> call = service.limparBancoDeDados();
        Response<Void> response = call.execute();
        return response.isSuccessful();
    }

}
