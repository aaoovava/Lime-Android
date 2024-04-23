package com.example.limeapp.core.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.limeapp.R;
import com.example.limeapp.core.abstract_base.AbstractDropFreezing;
import com.example.limeapp.core.dialogs.ReaskDialog;
import com.example.limeapp.core.enums.AbonimentGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AbonimentFreezeDropActivity extends AbstractDropFreezing {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afreeze_drop);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        ImageView backBut = findViewById(R.id.baсk);
        ImageView correctBut = findViewById(R.id.Yes);
        TextView date = findViewById(R.id.textView9);
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstScreen();
            }
        });
        // DropFreezing process
        users.child(userId).addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Date = (snapshot.child("afreeze_date").getValue().toString());
                date.setText(Date);
                long daysDiff = dataDifInDaysLocal(Date);
                String a = "Залишилося: ";
                if (daysDiff == 1) {
                    a = "Залишився: ";
                }

                correctBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String LastDate = (snapshot.child("aboniment_end_date").getValue().toString());
                        String aboniment_end_date = addDaysToDate(LastDate, -dataDifInDaysLocal(Date));
                        ReaskDialog reaskDialog = new ReaskDialog(false, aboniment_end_date, "", 0, AbonimentGroup.PERSONAL);
                        reaskDialog.show(getSupportFragmentManager(), "ReaskDialog");

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}