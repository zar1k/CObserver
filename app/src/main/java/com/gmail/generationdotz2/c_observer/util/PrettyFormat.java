package com.gmail.generationdotz2.c_observer.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.gmail.generationdotz2.c_observer.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PrettyFormat {

    public static String addPercentSign(String percentChange) {
        StringBuilder sb = new StringBuilder();
        return sb.append(percentChange).append("%").toString();
    }

    public static int changeColor(Context context, String percentChange) {
        final String negativePercentageChange = "-";
        final int percentMinusColor = ContextCompat.getColor(context, R.color.percent_minus);
        final int percentPlusColor = ContextCompat.getColor(context, R.color.percent_plus);
        return (percentChange.startsWith(negativePercentageChange, 0) ? percentMinusColor : percentPlusColor);
    }

    public static String prettyNumberFormat(String number) {
        Locale[] locales = NumberFormat.getAvailableLocales();
        double myNumber = Double.parseDouble(number);
        NumberFormat form = null;
        for (int j = 0; j < 4; ++j) {
            System.out.println("FORMAT");
            for (int i = 0; i < locales.length; ++i) {
                if (locales[i].getCountry().length() == 0) {
                    continue; // Skip language-only locales
                }
                System.out.print(locales[i].getDisplayName());
                switch (j) {
                    case 0:
                        form = NumberFormat.getInstance(locales[i]);
                        break;
                    case 1:
                        form = NumberFormat.getIntegerInstance(locales[i]);
                        break;
                    case 2:
                        form = NumberFormat.getCurrencyInstance(locales[i]);
                        break;
                    default:
                        form = NumberFormat.getPercentInstance(locales[i]);
                        break;
                }
                if (form instanceof DecimalFormat) {
                    System.out.print(": " + ((DecimalFormat) form).toPattern());
                }
                System.out.print(" -> " + form.format(myNumber));
                try {
                    System.out.println(" -> " + form.parse(form.format(myNumber)));
                } catch (ParseException e) {
                }
            }
        }
        return form.toString();
    }
}