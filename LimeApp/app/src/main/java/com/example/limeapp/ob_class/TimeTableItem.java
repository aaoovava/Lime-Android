package com.example.limeapp.ob_class;

import com.example.limeapp.Core.Adapters.TimeTableAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TimeTableItem {
    String name, owner, time1, time2;

    public TimeTableItem(String name, String owner, String time1, String time2) {
        this.name = name;
        this.owner = owner;
        this.time1 = time1;
        this.time2 = time2;
    }
    public TimeTableItem(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }
    public static ArrayList<TimeTableItem>sortTimeTableItemList(ArrayList<TimeTableItem>list){
        Collections.sort(list, new Comparator<TimeTableItem>() {
            @Override
            public int compare(TimeTableItem item1, TimeTableItem item2) {
                String[] time1Parts = item1.getTime1().split(":");
                String[] time2Parts = item2.getTime1().split(":");

                int hour1 = Integer.parseInt(time1Parts[0]);
                int minute1 = Integer.parseInt(time1Parts[1]);
                int hour2 = Integer.parseInt(time2Parts[0]);
                int minute2 = Integer.parseInt(time2Parts[1]);

                if (hour1 != hour2) {
                    return hour1 - hour2;
                } else {
                    return minute1 - minute2;
                }
            }
        });
        return list;
    }
}
