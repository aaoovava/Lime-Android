package com.example.limeapp.ob_class;

import android.graphics.drawable.Drawable;

public class FirstViewItem {
    private String MainTxt;
    private int CardElement;

    public  FirstViewItem(String meinTxt, int cardElement) {
        MainTxt = meinTxt;
        CardElement = cardElement;
    }

    public String getMeinTxt() {
        return MainTxt;
    }

    public void setMeinTxt(String meinTxt) {
        MainTxt = meinTxt;
    }

    public int getCardElement() {
        return CardElement;
    }

    public void setCardElement(int cardElement) {
        CardElement = cardElement;
    }
}
