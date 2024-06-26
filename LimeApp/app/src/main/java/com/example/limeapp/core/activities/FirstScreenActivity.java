package com.example.limeapp.core.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.limeapp.core.abstract_base.AbstractActivity;
import com.example.limeapp.core.dialogs.ChoseAboniment;
import com.example.limeapp.core.dialogs.TimeTableDialog;
import com.example.limeapp.R;
import com.example.limeapp.core.interfaces.listeners.OnItemClickListener;
import com.example.limeapp.ob_class.FirstViewItem;
import com.example.limeapp.core.adapters.FirstView_VpAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;

public class FirstScreenActivity extends AbstractActivity implements OnItemClickListener {

    FirebaseDatabase db;
    ArrayList<FirstViewItem> viePagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidthDp = (int) (displayMetrics.widthPixels / displayMetrics.density);
        int screenHeightDp = (int) (displayMetrics.heightPixels / displayMetrics.density);
        Boolean isBig = false;


        if (screenHeightDp <= 630 && (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            setContentView(R.layout.activity_first_screen_small);
        } else if (screenWidthDp >= 530) {
            isBig = true;
            setContentView(R.layout.activity_first_screen);
        } else {
            setContentView(R.layout.activity_first_screen);
        }


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));

        ViewPager2 viewPager2 = findViewById(R.id.pager1);
        SpringDotsIndicator dotsIndicator = findViewById(R.id.Adapter);
        ConstraintLayout constraintLayout = findViewById(R.id.View);
        TextView DateText = findViewById(R.id.dateProf);
        TextView DownText1 = findViewById(R.id.textView5);
        TextView DownText2 = findViewById(R.id.textView6);
        TextView CountText = findViewById(R.id.textView8);
        TextView CountNumber = findViewById(R.id.textView7);
        TextView UpTxt = findViewById(R.id.UpText1);

        DateText.setText(getCurrentDateFormatted());
        DisplayMetrics displayMetrics1 = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics1);
            ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
            //Txt %
            double screenHeight = displayMetrics1.heightPixels * 0.9;
            double screenWidth = displayMetrics1.widthPixels;
            layoutParams.width = layoutParams.width;
            layoutParams.height = (int) screenHeight;
            constraintLayout.setLayoutParams(layoutParams);

            DisplayMetrics displayMetricsTxt = new DisplayMetrics();
            WindowManager windowManagerTxt = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            if (windowManagerTxt != null && isBig == true) {

                windowManagerTxt.getDefaultDisplay().getMetrics(displayMetrics);
                //Txt %

                int textSize_yurname = (int) (screenWidth * 0.032);
                int txtSize_date = (int) (screenWidth * 0.035);
                int textSize_status = (int) (screenWidth * 0.030);
                int textSize_title = (int) (screenWidth * 0.06);
                int textSize_name = (int) (screenWidth * 0.042);
                UpTxt.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_title);
                DateText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_status);
                DownText1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_name);
                DownText2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_name);
                CountText.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize_date);
                CountNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_title);
            }

            users.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String usName = snapshot.child("name").getValue().toString();
                    try {
                        String[] name = Spase(usName);
                        UpTxt.setText("Привіт, " + name[1]);
                    } catch (Exception e) {
                        String[] name = Spase(usName);
                        UpTxt.setText("Привіт, " + name[0]);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            //INIT
            ImageView nav_Profile = findViewById(R.id.nav_profile);
            ImageView nav_Buy = findViewById(R.id.nav_buy);
            ImageView nav_verification = findViewById(R.id.imageView8);


            nav_Profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toProfile();

                }
            });
            nav_verification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toVerification();
                }
            });
            nav_Buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toBuy();
                }
            });

            viePagerItemArrayList = new ArrayList<>();
            FirstViewItem firstViewItem3 = new FirstViewItem("Графік групових занять", R.drawable.time_table);
            viePagerItemArrayList.add(firstViewItem3);
            FirstViewItem firstViewItem1 = new FirstViewItem("Дії з абонементом", R.drawable.actions_with_aboniment);
            viePagerItemArrayList.add(firstViewItem1);
            FirstViewItem firstViewItem2 = new FirstViewItem("Вигідні пропозиції", R.drawable.intresting_staff);
            viePagerItemArrayList.add(firstViewItem2);


            FirstView_VpAdapter firstViewVpAdapter = new FirstView_VpAdapter(viePagerItemArrayList, this);
            firstViewVpAdapter.setListener(this);

            viewPager2.setAdapter(firstViewVpAdapter);
            viewPager2.setClipToPadding(false);
            viewPager2.setClipChildren(false);
            viewPager2.setOffscreenPageLimit(3);
            viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
            dotsIndicator.attachTo(viewPager2);


        }
    }

    @Override
    public void onItemClick(int position) {
        try {
            if (position == 1) {
                ChoseAboniment choseAboniment = new ChoseAboniment();
                choseAboniment.show(getSupportFragmentManager(), "choseTable");
            } else if (position == 0) {
                TimeTableDialog timeTableDialog = new TimeTableDialog();
                timeTableDialog.show(getSupportFragmentManager(), "timeTable");
            }
        } catch (Exception e) {
            toVerification();
        }

    }


}