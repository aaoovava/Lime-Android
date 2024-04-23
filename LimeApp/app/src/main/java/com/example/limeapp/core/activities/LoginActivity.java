package com.example.limeapp.core.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.limeapp.R;
import com.example.limeapp.core.abstract_base.AbstractActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AbstractActivity {

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            toVerification();
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
        ButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phoneEdit.getText().toString().trim().equals("") || pswEdit.getText().toString().trim().equals("")) {

                    Toast toast = Toast.makeText(LoginActivity.this, "Заповніть усі поля", Toast.LENGTH_LONG);
                    toast.show();
                    imageIcon.setVisibility(View.VISIBLE);
                } else {
                    imageIcon.startAnimation(slideHorizontalAnimation);
                    imageIcon.setVisibility(View.INVISIBLE);
                    logFunc(phoneEdit.getText().toString().trim() + "@gmail.com", pswEdit.getText().toString());
                }
            }
        });
        toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegistration();
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
                toVerification();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(LoginActivity.this, "Неправельний номер чи пароль", Toast.LENGTH_LONG);
                imageIcon.setVisibility(View.VISIBLE);
                toast.show();
            }
        });
    }
}