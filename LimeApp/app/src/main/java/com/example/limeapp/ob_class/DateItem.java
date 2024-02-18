package com.example.limeapp.ob_class;

public class DateItem {
    String Date, Day;

    public DateItem(String date, String day) {
        Date = date;
        Day = day;
    }

    public String getDate() {
        return Date;
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
}
