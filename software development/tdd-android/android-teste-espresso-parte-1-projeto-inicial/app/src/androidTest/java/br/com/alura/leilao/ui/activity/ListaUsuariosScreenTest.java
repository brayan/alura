package br.com.alura.leilao.ui.activity;


import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import br.com.alura.leilao.BaseIntegrationTest;
import br.com.alura.leilao.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class ListaUsuariosScreenTest extends BaseIntegrationTest {

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> mainActivity = new ActivityTestRule<>(ListaLeilaoActivity.class);

    @Before
    public void setUp() {
        limparBancoDeDadosInterno();
    }

    @After
    public void tearDown() {
        limparBancoDeDadosInterno();
    }

    @Test
    public void deveAparecerUsuarioNaListaDeUsuariosQuandoCadastrarUsuario() {
        onView(allOf(withId(R.id.lista_leilao_menu_usuarios),
                withContentDescription("Usuários"),
                isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.lista_usuario_fab_adiciona), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.form_usuario_nome_edittext), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.form_usuario_nome_edittext), isDisplayed()))
                .perform(replaceText("Alex"), closeSoftKeyboard());

         onView(allOf(withId(android.R.id.button1), withText("Adicionar"), isDisplayed())).perform(scrollTo(), click());

         onView(allOf(withId(R.id.item_usuario_id_com_nome), isDisplayed())).check(matches(withText("(1) Alex")));
    }

}
