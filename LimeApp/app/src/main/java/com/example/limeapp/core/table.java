package com.example.limeapp.core;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.limeapp.R;

public class table extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.green));
        ImageView closeBut = findViewById(R.id.CloseButTab);

        closeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    public void toMain() {
        Intent intent = new Intent(this, VerificationActivity.class);
        startActivity(intent);
    }
}