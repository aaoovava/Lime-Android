package com.example.limeapp.Core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.limeapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            toMain();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        Animation slideHorizontalAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_horizontal);


        ImageView toReg = findViewById(R.id.imageView6);
        ImageView imageIcon = findViewById(R.id.imageView40);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ImageView ButtonLog = findViewById(R.id.imageView7);
        EditText phoneEdit = (EditText) findViewById(R.id.EditTextAge);
        EditText pswEdit = (EditText) findViewById(R.id.editTextTextPersonName2);
        //
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Client");
        ButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phoneEdit.getText().toString().trim().equals("") || pswEdit.getText().toString().trim().equals("")) {

                    Toast toast = Toast.makeText(LogActivity.this, "Заповніть усі поля", Toast.LENGTH_LONG);
                    toast.show();
                    imageIcon.setVisibility(View.VISIBLE);
                } else {
                    imageIcon.startAnimation(slideHorizontalAnimation);
                    imageIcon.setVisibility(View.INVISIBLE);
                    logFunc(phoneEdit.getText().toString() + "@gmail.com", pswEdit.getText().toString());

                }
            }
        });
        toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReg();
            }
        });

    }

    private void logFunc(String phone, String pass) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ImageView imageIcon = findViewById(R.id.imageView40);
        //progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(phone, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                toMain();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(LogActivity.this, "Неправельний номер чи пароль", Toast.LENGTH_LONG);
                imageIcon.setVisibility(View.VISIBLE);
                toast.show();
            }
        });
    }

    void toReg() {
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }

    void toMain() {
        Intent intent = new Intent(this, VerificationActivity.class);
        startActivity(intent);
    }

}