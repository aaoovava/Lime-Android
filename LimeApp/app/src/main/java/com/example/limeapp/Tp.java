package com.example.limeapp;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Tp extends AppCompatActivity {

    public void toFreeze() {
        Intent intent = new Intent(this, AFreeze.class);
        startActivity(intent);
    }

    public void toTable() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}
