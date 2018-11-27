package br.com.alura.leilao.ui.activity;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ListaLeilaoScreenTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(ListaLeilaoActivity.class, true, true);

    @Test
    public void deveAparecerUmLeilaoQuandoCarregarUmLeilaoNaApi() {
        onView(withText("Casa")).check(matches(isDisplayed()));
    }

}