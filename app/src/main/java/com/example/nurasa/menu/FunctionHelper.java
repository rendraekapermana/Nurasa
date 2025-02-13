package com.example.nurasa.menu;

import android.text.format.DateFormat;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class FunctionHelper {

    public static String rupiahFormat(int price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return "Rp " + formatter.format(price).replaceAll(",", ".");
    }

    public static String getToday() {
        Date date = Calendar.getInstance().getTime();
        return (String) DateFormat.format("d MMMM yyyy", date);
    }

}
