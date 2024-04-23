package com.example.limeapp.core.abstract_base;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractDropFreezing extends AbstractActivity {
    public Date getDateFromString(String data) {
        String dateString = data;
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long dataDifInDaysLocal(String Date) {
        Date Localdate = new Date();
        Date ALastDateD = getDateFromString(Date);
        LocalDate date1 = Localdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = ALastDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long daysDiff = ChronoUnit.DAYS.between(date1, date2);
        return daysDiff;
    }
    public String addDaysToDate(String strdate, long daysCount) {
        String dateString = strdate;
        String res = "";
        Date date = new Date();
        Date newDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = format.parse(dateString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, (int) daysCount);

            newDate = calendar.getTime();
            res = format.format(newDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;

    }
}
