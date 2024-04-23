package com.example.limeapp.core.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.limeapp.R;
import com.example.limeapp.core.abstract_base.AbstractFreezingActivity;
import com.example.limeapp.core.dialogs.ReaskDialog;
import com.example.limeapp.core.enums.AbonimentGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AbonimentFreezeActivity extends AbstractFreezingActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afreeze);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        TextView countOfInput = findViewById(R.id.CountOfU);
        TextView countOfD = findViewById(R.id.CountOfD);
        EditText dataEdit = findViewById(R.id.DataEdit);
        ImageView closeBut = findViewById(R.id.CloseBut);
        TextView Resultdate = findViewById(R.id.textDate);
        ImageView backBut = findViewById(R.id.baсk);
        ImageView correctBut = findViewById(R.id.Yes);
        ConstraintLayout rootView = findViewById(R.id.root_view);


        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstScreen();
            }
        });

        //Close keyboard by click
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });
        users.child(userId).addValueEventListener(new ValueEventListener() {
            //Freezing process
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Dres = (snapshot.child("afreeze_days").getValue().toString()) + " дн";
                countOfD.setText(Dres);
                countOfInput.setText("0 дн");

                dataEdit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String ALastDate = snapshot.child("aboniment_end_date").getValue().toString();
                        int Data = Integer.parseInt(snapshot.child("afreeze_days").getValue().toString());
                        if (s.toString() != "") {

                            closeBut.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    closeKeyboard();
                                    closeBut.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        String Input = s.toString() + " дн";
                        countOfInput.setText(Input);
                        try {
                            String a = String.valueOf(Data - Integer.parseInt(s.toString()) + " дн");
                            countOfD.setText(a);
                        } catch (Exception e) {
                            String Dres = String.valueOf(Data) + " дн";
                            countOfD.setText(Dres);
                        }
                        try {


                            if (Integer.parseInt(s.toString()) > Data) {
                                dataEdit.setText("14");
                            }
                        } catch (Exception e) {

                        }
                        try {
                            Resultdate.setText("Заморозка до " + getFutureDateAsString(Integer.parseInt(s.toString())));
                        } catch (Exception e) {
                            Resultdate.setText("");
                        }
                        correctBut.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String aboniment_end_date = addFewDaysAndGetDate(ALastDate, Integer.parseInt(s.toString()));
                                String countOfFreeze =  s.toString();
                                String AFreezeDate = getFutureDateAsString(Integer.parseInt(s.toString()));
                                ReaskDialog reaskDialog = new ReaskDialog(true, aboniment_end_date,AFreezeDate, Integer.parseInt(countOfFreeze), AbonimentGroup.PERSONAL);
                                reaskDialog.show(getSupportFragmentManager(),"ReaskDialog");

                            }
                        });

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}