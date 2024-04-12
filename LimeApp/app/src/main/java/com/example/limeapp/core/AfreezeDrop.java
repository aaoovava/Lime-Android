package com.example.limeapp.core;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.limeapp.R;
import com.example.limeapp.core.dialogs.ReaskDialog;
import com.example.limeapp.core.enums.AbonimentGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AfreezeDrop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afreeze_drop);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference("Client");
        String userId = user.getUid();

        ImageView backBut = findViewById(R.id.baсk);
        ImageView correctBut = findViewById(R.id.Yes);
        TextView date = findViewById(R.id.textView9);


        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
            }
        });
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
//
//                        HashMap<String, Object> j = new HashMap<>();
//                        j.put("aboniment_end_date", addDaysToDate(LastDate, -dataDifInDaysLocal(Date)));
//                        j.put("aboniment_status", "1");
//                        j.put("afreeze_days", "0");
//                        users.child(auth.getCurrentUser().getUid()).updateChildren(j);
//                        toMain();

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

    public void toMain() {
        Intent intent = new Intent(this, VerificationActivity.class);
        startActivity(intent);
    }

    public Date SetDate(String data) {
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
        Date ALastDateD = SetDate(Date);
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