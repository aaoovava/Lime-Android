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
            String[]formattedDateL = formattedDate.split(" ");
            String[]formattedDayL = formattedDay.split("") ;
            formattedDayL[0] = formattedDayL[0].toUpperCase();
            String fomattedDay1 = formattedDateL[1];
            String[] fomattedDay1_1 = fomattedDay1.split("");
            fomattedDay1_1[0] = fomattedDay1_1[0].toUpperCase();
            fomattedDay1_1[fomattedDay1_1.length - 1] = "";
            String formattedDay2 = formattedDayL[0];
            String date1 = formattedDateL[0];
            list.add(new DateItem( date1 + " " + listToString(fomattedDay1_1),listToString(formattedDayL)));
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
