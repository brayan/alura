package br.com.alura.aluraviagens.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {

    public static String formatarPrecoParaReal(BigDecimal valor) {
        NumberFormat format = DecimalFormat.getCurrencyInstance(new Locale("pt", "br"));
        return format.format(valor).replace("R$", "R$ ");
    }

}
