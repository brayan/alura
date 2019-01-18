package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import br.com.alura.leilao.BaseIntegrationTest;
import br.com.alura.leilao.R;
import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.formatter.FormatadorDeMoeda;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import org.junit.*;

import java.io.IOException;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

public class LancesLeilaoScreenTest extends BaseIntegrationTest {


    @Rule
    public ActivityTestRule<ListaLeilaoActivity> mainActivity = new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);

    @Before
    public void setUp() throws IOException {
        limparBaseDeDadosDaApi();
        limparBancoDeDadosInterno();
    }

    @After
    public void tearDown() throws IOException {
        limparBaseDeDadosDaApi();
        limparBancoDeDadosInterno();
    }

    @Test
    public void deveAtualizarLancesDoLeilaoQuandoreceberUmLance() throws IOException, InterruptedException {
        tentarSalvarLeilaoNaApi(new Leilao("Carro"));
        mainActivity.launchActivity(new Intent());
        onView(withId(R.id.lista_leilao_recyclerview)).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.lances_leilao_fab_adiciona), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.alertTitle), withText("Usuários não encontrados"))).check(matches(isDisplayed()));
        onView(allOf(withId(android.R.id.message), withText("Não existe usuários cadastrados! Cadastre um usuário para propor o lance."))).check(matches(isDisplayed()));
        onView(allOf(withText("CADASTRAR USUÁRIO"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.lista_usuario_fab_adiciona), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.form_usuario_nome_edittext), isDisplayed()))
                .perform(click(), typeText("Alex"), closeSoftKeyboard());

        onView(allOf(withId(android.R.id.button1), withText("Adicionar"), isDisplayed())).perform(scrollTo(), click());

        pressBack();

        proporNovoLance("200", 1, "Alex");

        FormatadorDeMoeda formatador = new FormatadorDeMoeda();
        onView(withId(R.id.lances_leilao_maior_lance)).check(matches(allOf(withText(formatador.formata(200)), isDisplayed())));
        onView(withId(R.id.lances_leilao_menor_lance)).check(matches(allOf(withText(formatador.formata(200)), isDisplayed())));
        onView(withId(R.id.lances_leilao_maiores_lances)).check(matches(allOf(withText(formatador.formata(200) + " - (1) Alex\n"), isDisplayed())));
    }

    @Test
    public void deveAtualizarLancesDoLeilaoQuandoReceberTresLances() throws IOException {
        tentarSalvarLeilaoNaApi(new Leilao("Carro"));
        tentarSalvarUsuariosNoBancoDeDados(new Usuario("Alex"), new Usuario("Fran"));

        mainActivity.launchActivity(new Intent());
        onView(withId(R.id.lista_leilao_recyclerview)).perform(actionOnItemAtPosition(0, click()));

        proporNovoLance("200", 1, "Alex");
        proporNovoLance("300", 2, "Fran");
        proporNovoLance("400", 1, "Alex");

        FormatadorDeMoeda formatador = new FormatadorDeMoeda();
        onView(withId(R.id.lances_leilao_maior_lance)).check(matches(allOf(withText(formatador.formata(400)), isDisplayed())));
        onView(withId(R.id.lances_leilao_menor_lance)).check(matches(allOf(withText(formatador.formata(200)), isDisplayed())));
        onView(withId(R.id.lances_leilao_maiores_lances)).check(matches(allOf(withText(formatador.formata(400) + " - (1) Alex\n" +
                formatador.formata(300) + " - (2) Fran\n" +
                formatador.formata(200) + " - (1) Alex\n"), isDisplayed())));
    }

    @Test
    public void deveAtualizarLancesDoLeilaoQuandoReceberUmLanceMuitoAlto() throws IOException {
        tentarSalvarLeilaoNaApi(new Leilao("Carro"));
        tentarSalvarUsuariosNoBancoDeDados(new Usuario("Alex"));

        mainActivity.launchActivity(new Intent());
        onView(withId(R.id.lista_leilao_recyclerview)).perform(actionOnItemAtPosition(0, click()));

        proporNovoLance("2000000000", 1, "Alex");

        FormatadorDeMoeda formatador = new FormatadorDeMoeda();
        onView(withId(R.id.lances_leilao_maior_lance)).check(matches(allOf(withText(formatador.formata(2000000000)), isDisplayed())));
        onView(withId(R.id.lances_leilao_menor_lance)).check(matches(allOf(withText(formatador.formata(2000000000)), isDisplayed())));
        onView(withId(R.id.lances_leilao_maiores_lances)).check(matches(allOf(withText(formatador.formata(2000000000) + " - (1) Alex\n"), isDisplayed())));
    }

    private void proporNovoLance(String lance, int idUsuario, String nomeUsuario) {
        onView(allOf(withId(R.id.lances_leilao_fab_adiciona), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.alertTitle), withText("Novo lance"))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.form_lance_valor_edittext), isDisplayed())).perform(click(), typeText(lance), closeSoftKeyboard());
        onView(allOf(withId(R.id.form_lance_usuario), isDisplayed())).perform(click());

        onData(is(new Usuario(idUsuario, nomeUsuario))).inRoot(isPlatformPopup()).perform(click());
        onView(allOf(withText("Propor"), isDisplayed())).perform(click());
    }

    private void tentarSalvarUsuariosNoBancoDeDados(Usuario... usuarios) {
        UsuarioDAO dao = new UsuarioDAO(InstrumentationRegistry.getTargetContext());

        for (Usuario usuario : usuarios) {
            Usuario usuarioSalvo = dao.salva(usuario);
            if (usuarioSalvo == null) {
                Assert.fail("Usuários não foi salvo");
            }
        }
    }


}
