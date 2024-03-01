package com.example.limeapp.holder;

import android.util.TypedValue;
import android.widget.TextView;

public class TextHolder {
    public static void SetTextSize(int screenHeight, TextView textView, double count){
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (screenHeight * count));
    }
}
