package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.alura.leilao.BaseIntegrationTest;
import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Leilao;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static br.com.alura.leilao.matchers.ViewMatcher.apareceLeilaoNaPosicao;

public class ListaLeilaoScreenTest extends BaseIntegrationTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(ListaLeilaoActivity.class, true, false);

    @Before
    public void setUp() throws IOException {
        limparBaseDeDadosDaApi();
    }

    @After
    public void tearDown() throws IOException {
        limparBaseDeDadosDaApi();
    }

    @Test
    public void deveAparecerUmLeilaoQuandoCarregarUmLeilaoNaApi() throws IOException {
        tentarSalvarLeilaoNaApi(new Leilao("Casa"));
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.lista_leilao_recyclerview)).check(matches(apareceLeilaoNaPosicao(0, "Casa", 0.00)));
    }

    @Test
    public void deveAparecerDoisLeiloesQuandoCarregarDoisLeiloesDaApi() throws IOException {
        tentarSalvarLeilaoNaApi(new Leilao("Carro"), new Leilao("Computador"));
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.lista_leilao_recyclerview)).check(matches(apareceLeilaoNaPosicao(0, "Carro", 0.00)));
        onView(withId(R.id.lista_leilao_recyclerview)).check(matches(apareceLeilaoNaPosicao(1, "Computador", 0.00)));
    }

    @Test
    public void deveAparecerUltimoLeilaoQuandoCarregarDezLeiloesDaApi() throws IOException {
        tentarSalvarLeilaoNaApi(new Leilao("Carro"),
                new Leilao("Computador"),
                new Leilao("TV"),
                new Leilao("Notebook"),
                new Leilao("Console"),
                new Leilao("Jogo"),
                new Leilao("Estante"),
                new Leilao("Quadro"),
                new Leilao("Smartphone"),
                new Leilao("Casa"));

        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.lista_leilao_recyclerview))
                .perform(scrollToPosition(9))
                .check(matches(apareceLeilaoNaPosicao(9, "Casa", 0.00)));
    }


}