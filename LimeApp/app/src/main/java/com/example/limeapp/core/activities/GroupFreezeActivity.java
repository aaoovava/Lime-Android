package com.example.limeapp.core.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

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

public class GroupFreezeActivity extends AbstractFreezingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gfreeze);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        TextView countOfInput = findViewById(R.id.CountOfU);
        TextView countOfD = findViewById(R.id.CountOfD);
        TextView Resultdate = findViewById(R.id.textDate);
        EditText dataEdit = findViewById(R.id.DataEdit);
        ImageView backBut = findViewById(R.id.baсk);
        ImageView correctBut = findViewById(R.id.Yes);

        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toVerification();
            }
        });
        users.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Dres = (snapshot.child("gfreeze_days").getValue().toString()) + " дн";
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
                        String ALastDate = snapshot.child("group_t_end_date").getValue().toString();
                        int Data = Integer.parseInt(snapshot.child("gfreeze_days").getValue().toString());
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
                                dataEdit.setText("");
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
                                String group_t_end_date = addFewDaysAndGetDate(ALastDate, Integer.parseInt(s.toString()));
                                String gfreeze_date = getFutureDateAsString(Integer.parseInt(s.toString()));
                                String countOfFreeze =  s.toString();
                                try {
                                ReaskDialog reaskDialog = new ReaskDialog(true, group_t_end_date,gfreeze_date, Integer.parseInt(countOfFreeze), AbonimentGroup.GROUP);
                                reaskDialog.show(getSupportFragmentManager(), "ReaskDialog");
                                }catch (Exception e){
                                    toVerification();
                                }
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
