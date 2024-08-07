package com.example.limeapp.core.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.limeapp.core.abstract_base.AbstractActivity;
import com.example.limeapp.core.adapters.BuyHistoryAdapter;
import com.example.limeapp.core.dialogs.BuyDialogFrame;
import com.example.limeapp.core.enums.AbonimentGroup;
import com.example.limeapp.core.enums.Metrics;
import com.example.limeapp.holder.TextHolder;
import com.example.limeapp.R;
import com.example.limeapp.ob_class.SalesItem;
import com.example.limeapp.ob_class.UserBuys;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyActivity extends AbstractActivity{
    private RecyclerView recyclerView;
    private Metrics metrics;
    private BuyHistoryAdapter buyHistoryAdapter;
    private ArrayList<UserBuys>list;
    private ImageView GroupBuy, PersonalBuy;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics2 = getResources().getDisplayMetrics();
        int screenWidthDp = (int) (displayMetrics2.widthPixels / displayMetrics2.density);
        int screenHeightDp = (int) (displayMetrics2.heightPixels / displayMetrics2.density);
        if (screenHeightDp <= 630 && (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            setContentView(R.layout.buy_small);
            metrics = Metrics.SMALL;
        } else if (screenWidthDp >= 530) {
            setContentView(R.layout.buy_activity);
            metrics = Metrics.NORMAL;
        } else {
            // По умолчанию, если не подходит ни одно из условий
            setContentView(R.layout.buy_activity);
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));

        ImageView nav_verefication = findViewById(R.id.nav_verefication);
        ImageView nav_profil = findViewById(R.id.nav_profile);
        ImageView nav_first = findViewById(R.id.nav_firstScreen);
        TextView TopText = findViewById(R.id.BuyTxtTop);
        TextView TextView15 = findViewById(R.id.textView15);
        TextView TextView16 = findViewById(R.id.textView16);
        ImageView sheetImage = findViewById(R.id.imageView52);
        TextView sheetText = findViewById(R.id.textView17);
        GroupBuy = findViewById(R.id.GroupBuy);
        PersonalBuy = findViewById(R.id.PersonalBuy);
        recyclerView = findViewById(R.id.buyHistory);

        DatabaseReference personalSalesRef = db.getReference("PersonalSales");
        DatabaseReference groupSalesRef = db.getReference("GroupSales");
        HashMap<String,SalesItem>list1 = new HashMap<>();
        list1.put("Групові заняття",new SalesItem("Групові заняття","(Місяць/12 занять)","600"));
        list1.put("Разове відвідування",new SalesItem("Разове відвідування","(групові)","100"));
        list1.put("Квартал",new SalesItem("Квартал","(Місяць/безліміт)","1500"));
        groupSalesRef.setValue(list1);

        GroupBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyDialogFrame buyDialogFrame = new BuyDialogFrame(AbonimentGroup.GROUP);
                buyDialogFrame.show(getSupportFragmentManager(),"buyDialogFrame");
            }
        });
        //BuySale
        PersonalBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyDialogFrame buyDialogFrame = new BuyDialogFrame(AbonimentGroup.PERSONAL);
                buyDialogFrame.show(getSupportFragmentManager(),"buyDialogFrame");
            }
        });

        //BuyHistory
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        buyHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                buyHistoryAdapter = new BuyHistoryAdapter(BuyActivity.this,list);
                recyclerView.setAdapter(buyHistoryAdapter);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserBuys userbuys = dataSnapshot.getValue(UserBuys.class);
                    list.add(userbuys);
                }

                buyHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DisplayMetrics displayMetrics1 = new DisplayMetrics();
        WindowManager windowManager1 = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager1 != null) {
            windowManager1.getDefaultDisplay().getMetrics(displayMetrics1);
            //Txt %
            int screenHeight1 = displayMetrics1.heightPixels;
            int screenWidth1 = displayMetrics1.widthPixels;
            //buttonSheet
            ConstraintLayout bottomSheet = findViewById(R.id.sheet);
            BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
            if (metrics == Metrics.SMALL)bottomSheetBehavior.setPeekHeight((int) (screenHeight1 * 0.22));
            else bottomSheetBehavior.setPeekHeight((int) (screenHeight1 * 0.2));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            //change button by the state
            bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        recyclerView.setVisibility(View.VISIBLE);
                        // Запускаем анимацию изменения цвета текста
                        int startColor = sheetText.getCurrentTextColor();
                        int endColor = Color.parseColor("#80F988");

                        ValueAnimator textColorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
                        textColorAnimator.setDuration(200); // Длительность анимации в миллисекундах
                        textColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animator) {
                                sheetText.setTextColor((int) animator.getAnimatedValue());
                            }
                        });
                        textColorAnimator.start();

                        // Запускаем анимацию изменения изображения
                        sheetImage.setImageResource(R.drawable.buy_top_line_on);
                    } else {
                        // Запускаем анимацию изменения цвета текста
                        int startColor = sheetText.getCurrentTextColor();
                        int endColor = Color.parseColor("#545F71");
                        recyclerView.setVisibility(View.INVISIBLE);
                        recyclerView.scrollToPosition(0);

                        ValueAnimator textColorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
                        textColorAnimator.setDuration(200); // Длительность анимации в миллисекундах
                        textColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animator) {
                                sheetText.setTextColor((int) animator.getAnimatedValue());
                            }
                        });
                        textColorAnimator.start();

                        // Запускаем анимацию изменения изображения
                        sheetImage.setImageResource(R.drawable.buy_top_line);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // Вызывается при изменении состояния свайпа, но для данной задачи onStateChanged достаточно
                    recyclerView.setVisibility(View.INVISIBLE);
                }
            });
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //Txt %
            int screenHeight = displayMetrics.heightPixels;
            int screenWidth = displayMetrics.widthPixels;

            TextHolder.SetTextSize(screenHeight,TopText,0.035);
            TextHolder.SetTextSize(screenHeight,TextView15,0.035);
            TextHolder.SetTextSize(screenHeight,TextView16,0.035);
        }


        nav_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstScreen();
            }
        });

        nav_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfile();
            }
        });

        nav_verefication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toVerification();
            }
        });

    }
}