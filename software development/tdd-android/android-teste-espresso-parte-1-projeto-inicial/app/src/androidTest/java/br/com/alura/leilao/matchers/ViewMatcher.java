package br.com.alura.leilao.matchers;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import br.com.alura.leilao.R;
import br.com.alura.leilao.formatter.FormatadorDeMoeda;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class ViewMatcher {


    public static Matcher<? super View> apareceLeilaoNaPosicao(final int posicao,
                                                               final String descricaoEsperada,
                                                               final double maiorLanceEsperado) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            private final Matcher<View> displayed = isDisplayed();
            private final FormatadorDeMoeda formatador = new FormatadorDeMoeda();
            private final String maiorLanceFormatado = formatador.formata(maiorLanceEsperado);

            @Override
            public void describeTo(Description description) {
                description.appendText("View com descrição ").appendValue(descricaoEsperada)
                        .appendText(", maior lance ").appendValue(maiorLanceFormatado)
                        .appendText(" na posição ").appendValue(posicao);
                description.appendText(" ").appendDescriptionOf(displayed);
            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                RecyclerView.ViewHolder viewHolder = item.findViewHolderForAdapterPosition(posicao);
                if (viewHolder == null) {
                    throw new IndexOutOfBoundsException("ViewHolder na posição " + posicao + " não foi encontrada");
                }

                boolean temDescricaoEsperada = apareceDescricaoEsperada(viewHolder.itemView);
                boolean temMaiorLanceEsperado = apareceMaiorLance(viewHolder.itemView);

                return temDescricaoEsperada && temMaiorLanceEsperado && displayed.matches(viewHolder.itemView);
            }

            private boolean apareceMaiorLance(View view) {
                TextView textViewMaiorLance = view.findViewById(R.id.item_leilao_maior_lance);
                return textViewMaiorLance.getText().toString().equals(maiorLanceFormatado)
                        && displayed.matches(textViewMaiorLance);
            }

            private boolean apareceDescricaoEsperada(View view) {
                TextView textViewDescricao = view.findViewById(R.id.item_leilao_descricao);
                return textViewDescricao.getText().toString().equals(descricaoEsperada)
                        && displayed.matches(textViewDescricao);
            }
        };
    }

}
