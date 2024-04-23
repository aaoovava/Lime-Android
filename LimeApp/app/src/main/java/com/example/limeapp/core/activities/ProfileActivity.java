package com.example.limeapp.core.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.limeapp.R;
import com.example.limeapp.core.abstract_base.AbstractActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AbstractActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));

        ImageView NavVerification = findViewById(R.id.nav_verefication);
        ImageView navBuy = findViewById(R.id.nav_buy);
        ImageView navFirstView = findViewById(R.id.nav_firstScreen);
        TextView upTxt = findViewById(R.id.UpText1);
        TextView dateTxt = findViewById(R.id.dateProf);

        ImageView LogOut = findViewById(R.id.changePassword);

        dateTxt.setText(getCurrentDateFormatted());
        users.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String usName = snapshot.child("name").getValue().toString();
                try {
                    String[] name = Spase(usName);
                    upTxt.setText("Привіт, " + name[1]);
                } catch (Exception e) {
                    String[] name = Spase(usName);
                    upTxt.setText("Привіт, " + name[0]);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        NavVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toVerification();
            }
        });
        navBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBuy();
            }
        });
        navFirstView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstScreen();
            }
        });
    }
}
