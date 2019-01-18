package br.com.alura.leilao;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import java.io.IOException;

import br.com.alura.leilao.api.retrofit.client.TesteWebClient;
import br.com.alura.leilao.model.Leilao;

import static org.junit.Assert.fail;

abstract public class BaseIntegrationTest {

    private static final String ERRO_FALHA_LIMPEZA_DE_DADOS_DA_API = "Banco de dados não foi limpo";
    private static final String ERRO_FALHA_SALVAR_LEILAO = "Leilão não foi salvo: ";

    private final TesteWebClient webClient = new TesteWebClient();

    protected void limparBaseDeDadosDaApi() throws IOException {
        boolean bancoNaoFoiLimpo = !webClient.limparBancoDeDados();

        if (bancoNaoFoiLimpo) {
            fail(ERRO_FALHA_LIMPEZA_DE_DADOS_DA_API);
        }
    }

    protected void tentarSalvarLeilaoNaApi(Leilao... leiloes) throws IOException {
        for (Leilao leilao : leiloes) {
            Leilao leilaoSalvo = webClient.salvar(leilao);
            if (leilaoSalvo == null) {
                fail(ERRO_FALHA_SALVAR_LEILAO + leilao.getDescricao());
            }
        }
    }

    protected void limparBancoDeDadosInterno() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        appContext.deleteDatabase(BuildConfig.BANCO_DE_DADOS);
    }

}
