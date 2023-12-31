package com.example.limeapp.Core;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.limeapp.R;
import com.example.limeapp.ob_class.User;
import com.example.limeapp.ob_class.User_Buys;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);
        //Init all values
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        ImageView TxtButLog = findViewById(R.id.textButLog);
        ImageView imageIcon = findViewById(R.id.imageView40);
        Animation slideHorizontalAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_horizontal);
        ImageView RegButton = findViewById(R.id.button_reg);

        TextView Name = findViewById(R.id.PIB);
        TextView Age = (TextView) findViewById(R.id.EditTextAge);
        TextView Phone = (TextView) findViewById(R.id.EditTextPhone);
        TextView Pwd = (TextView) findViewById(R.id.EditTextPass);
        TextView RPwd = (TextView) findViewById(R.id.EditTextPassRep);
        //
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Client");


        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Age.getText().toString().trim().equals("") || Phone.getText().toString().trim().equals("") || Pwd.getText().toString().trim().equals("") || RPwd.getText().toString().trim().equals("") || Name.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(RegActivity.this, "Заповніть усі поля", Toast.LENGTH_LONG);
                    imageIcon.startAnimation(slideHorizontalAnimation);
                    toast.show();

                } else if (!RPwd.getText().toString().equals(Pwd.getText().toString())) {
                    Toast toast = Toast.makeText(RegActivity.this, "Пролі не співпадають", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    imageIcon.startAnimation(slideHorizontalAnimation);
                    imageIcon.setVisibility(View.INVISIBLE);

                    auth.createUserWithEmailAndPassword(Phone.getText().toString() + "@gmail.com", Pwd.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            HashMap<String, User_Buys> userBuysList = new HashMap<>();
                            User_Buys user_buy = new User_Buys("Персональний абонемент", "650","21.11.2023");
                            User_Buys user_buy1 = new User_Buys("Вода", "250","21.02.2023");
                            User_Buys user_buy2 = new User_Buys("Турник", "650","21.08.2023");
                            User_Buys user_buy3 = new User_Buys("Скинувся на ручку", "150","21.05.2023");
                            userBuysList.put(user_buy.getName_of_buy(), user_buy);
                            userBuysList.put(user_buy1.getName_of_buy(), user_buy1);
                            userBuysList.put(user_buy2.getName_of_buy(), user_buy2);
                            userBuysList.put(user_buy3.getName_of_buy(), user_buy3);

                            User user = new User(Name.getText().toString(), Phone.getText().toString(), Pwd.getText().toString(), Age.getText().toString(), "--.--.----", "--.--.----", "3", "--.--.----", "--.--.----", "3", "", "14", "14", "n", "n", "0", userBuysList);
                            users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast toast = Toast.makeText(RegActivity.this, "Регістрація успішна", Toast.LENGTH_LONG);
                                    toast.show();
                                    I();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast toast = Toast.makeText(RegActivity.this, "Регістраційна помилка", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast toast = Toast.makeText(RegActivity.this, "Регістраційна помилка", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }

            }
        });
        TxtButLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                I();
            }

        });
    }

    void I() {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }


}
