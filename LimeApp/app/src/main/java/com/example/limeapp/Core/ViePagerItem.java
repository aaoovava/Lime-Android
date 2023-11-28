package com.example.limeapp.Core;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class ViePagerItem {
    String Uname, Sname, AorB_name, start_date, end_date, Pimg, StartDate, EndDate, CountOfGT, StatusTxt;
    Drawable ButImage, ButImage2, StatusImage;
    int color_status;

    public ViePagerItem(String uname, String sname, String aorB_name, String start_date, String end_date, String pimg, String startDate, String endDate, String countOfGT, Drawable butImage, Drawable butImage2, Drawable statusImage, Integer color_status, String statusTxt) {
        Uname = uname;
        Sname = sname;
        AorB_name = aorB_name;
        this.start_date = start_date;
        this.end_date = end_date;
        Pimg = pimg;
        StartDate = startDate;
        EndDate = endDate;
        CountOfGT = countOfGT;
        StatusTxt = statusTxt;
        ButImage = butImage;
        ButImage2 = butImage2;
        StatusImage = statusImage;
        this.color_status = color_status;
    }
}
