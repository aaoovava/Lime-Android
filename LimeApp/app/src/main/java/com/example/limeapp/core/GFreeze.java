package com.example.limeapp.core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class GFreeze extends AppCompatActivity {


    FirebaseDatabase db;

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


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference("Client");
        String userId = user.getUid();
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
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
                            Resultdate.setText("Заморозка до " + getDate1(Integer.parseInt(s.toString())));
                        } catch (Exception e) {
                            Resultdate.setText("");
                        }
                        correctBut.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                HashMap<String, Object> j = new HashMap<>();
//                                j.put("group_t_end_date", getDate(ALastDate, Integer.parseInt(s.toString())));
//                                j.put("group_t_status", "2");
//                                j.put("gfreeze_date", getDate1(Integer.parseInt(s.toString())));
//                                j.put("gfreeze_days", "0");
//                                users.child(auth.getCurrentUser().getUid()).updateChildren(j);
//                                toMain();
                                String group_t_end_date = getDate(ALastDate, Integer.parseInt(s.toString()));
                                String gfreeze_date = getDate1(Integer.parseInt(s.toString()));
                                String countOfFreeze =  s.toString();
                                try {
                                ReaskDialog reaskDialog = new ReaskDialog(true, group_t_end_date,gfreeze_date, Integer.parseInt(countOfFreeze), AbonimentGroup.GROUP);
                                reaskDialog.show(getSupportFragmentManager(), "ReaskDialog");
                                }catch (Exception e){
                                    toMain();
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

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public String getDate(String strdate, int daysCount) {
        String dateString = strdate;
        String res = "";
        Date date = new Date();
        Date newDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = format.parse(dateString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, daysCount);

            newDate = calendar.getTime();
            res = format.format(newDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;

    }

    public String getDate1(int daysCount) {

        String res = "";
        Date date = new Date();
        Date newDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysCount);

        newDate = calendar.getTime();
        res = format.format(newDate);

        return res;

    }

    public void toMain() {
        Intent intent = new Intent(this, VerificationActivity.class);
        startActivity(intent);
    }
}
