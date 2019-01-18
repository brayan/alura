package br.com.alura.aluraviagens.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {

    public static final String PATTERN_DAY_MONTH = "dd/MM";
    public static final String PATTERN_YEAR = "yyyy";

    public static String formatarDataDaViagem(int dias) {
        Calendar hoje = Calendar.getInstance();
        Calendar dataFinal = Calendar.getInstance();
        dataFinal.add(Calendar.DATE, dias);

        SimpleDateFormat todayFormat = new SimpleDateFormat(PATTERN_DAY_MONTH);
        SimpleDateFormat finalDateFormat = new SimpleDateFormat(PATTERN_DAY_MONTH + " 'de' " + PATTERN_YEAR);

        return todayFormat.format(hoje.getTime()) + " - " + finalDateFormat.format(dataFinal.getTime());
    }

}
