package com.example.limeapp.ob_class;

import com.example.limeapp.Core.Adapters.TimeTableAdapter;

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
}
