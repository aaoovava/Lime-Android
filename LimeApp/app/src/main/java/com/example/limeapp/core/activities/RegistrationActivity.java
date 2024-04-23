package com.example.limeapp.core.activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.limeapp.R;
import com.example.limeapp.core.abstract_base.AbstractActivity;
import com.example.limeapp.ob_class.User;
import com.example.limeapp.ob_class.UserBuys;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class RegistrationActivity extends AbstractActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);
        //Init all values
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        ImageView txtButLog = findViewById(R.id.textButLog);
        ImageView imageIcon = findViewById(R.id.imageView40);
        Animation slideHorizontalAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_horizontal);
        ImageView regButton = findViewById(R.id.button_reg);

        TextView name = findViewById(R.id.PIB);
        TextView age = (TextView) findViewById(R.id.EditTextAge);
        TextView phone = (TextView) findViewById(R.id.EditTextPhone);
        TextView pass = (TextView) findViewById(R.id.EditTextPass);
        TextView rePass = (TextView) findViewById(R.id.EditTextPassRep);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (age.getText().toString().trim().equals("") || phone.getText().toString().trim().equals("") || pass.getText().toString().trim().equals("") || rePass.getText().toString().trim().equals("") || name.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(RegistrationActivity.this, "Заповніть усі поля", Toast.LENGTH_LONG);
                    imageIcon.startAnimation(slideHorizontalAnimation);
                    toast.show();

                } else if (!rePass.getText().toString().equals(pass.getText().toString())) {
                    Toast toast = Toast.makeText(RegistrationActivity.this, "Пролі не співпадають", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    imageIcon.startAnimation(slideHorizontalAnimation);
                    imageIcon.setVisibility(View.INVISIBLE);

                    auth.createUserWithEmailAndPassword(phone.getText().toString() + "@gmail.com", pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            HashMap<String, UserBuys> userBuysList = new HashMap<>();

                            User user = new User(name.getText().toString(), phone.getText().toString(), pass.getText().toString(), age.getText().toString(), "--.--.----", "--.--.----", "3", "--.--.----", "--.--.----", "3", "", "14", "14", "n", "n", "0", userBuysList);
                            users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast toast = Toast.makeText(RegistrationActivity.this, "Регістрація успішна", Toast.LENGTH_LONG);
                                    toast.show();
                                    toLogin();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast toast = Toast.makeText(RegistrationActivity.this, "Регістраційна помилка", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast toast = Toast.makeText(RegistrationActivity.this, "Регістраційна помилка", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }

            }
        });
        txtButLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }

        });
    }
}
