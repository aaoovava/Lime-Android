package com.example.limeapp.ob_class;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class DateItem {
    String Date, Day;

    public DateItem(String date, String day) {
        Date = date;
        Day = day;
    }

    public String getDate() {
        return Date;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<DateItem> generateWeek() {
        ArrayList<DateItem> list = new ArrayList<>();
        LocalDate today = LocalDate.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM", new Locale("uk", "UA"));
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", new Locale("uk", "UA"));

        for (int i = 0; i < 7; i++) {
            String formattedDate = today.format(dateFormatter);
            String formattedDay = today.format(dayFormatter);
            String[]formattedDateL = formattedDate.split("");
            String[]formattedDayL = formattedDay.split("") ;
            formattedDayL[0] = formattedDayL[0].toUpperCase();
            formattedDateL[3] = formattedDateL[3].toUpperCase();
            formattedDateL[formattedDateL.length - 1] = "";
            list.add(new DateItem(listToString(formattedDateL), listToString(formattedDayL)));
            today = today.plusDays(1);
        }

        return list;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }
    public static String listToString(String[]list){
        String res = "";
        for (String s: list){
            res += s;
        }
        return res;
    }
}
