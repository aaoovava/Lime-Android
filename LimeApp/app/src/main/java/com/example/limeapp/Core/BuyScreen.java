package com.example.limeapp.Core;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.limeapp.Holder.TextHolder;
import com.example.limeapp.R;
import com.example.limeapp.ob_class.User_Buys;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase db;
    BuyHistoryAdapter buyHistoryAdapter;
    ArrayList<User_Buys>list;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_activity);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));

        ImageView nav_verefication = findViewById(R.id.nav_verefication);
        ImageView nav_profil = findViewById(R.id.nav_profile);
        ImageView nav_first = findViewById(R.id.nav_firstScreen);
        TextView TopText = findViewById(R.id.BuyTxtTop);
        TextView TextView14 = findViewById(R.id.textView14);
        TextView TextView15 = findViewById(R.id.textView15);
        TextView TextView16 = findViewById(R.id.textView16);
        TextView TextView2 = findViewById(R.id.textView2);
        ImageView sheetImage = findViewById(R.id.imageView52);
        TextView sheetText = findViewById(R.id.textView17);
        recyclerView = findViewById(R.id.buyHistory);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        String userId = user.getUid();
        DatabaseReference buyHistory = db.getReference("Client/" + userId +"/user_buys");



        //BuyHistory
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        buyHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                buyHistoryAdapter = new BuyHistoryAdapter(BuyScreen.this,list);
                recyclerView.setAdapter(buyHistoryAdapter);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User_Buys user_buys = dataSnapshot.getValue(User_Buys.class);
                    list.add(user_buys);
                }

                buyHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //buttonSheet
        ConstraintLayout bottomSheet = findViewById(R.id.sheet);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(400);
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
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //Txt %
            int screenHeight = displayMetrics.heightPixels;
            int screenWidth = displayMetrics.widthPixels;

            // Получите ссылку на ScrollView по его ID
            ScrollView scrollView = findViewById(R.id.scrollView3);

            int newHeightInPixels = 0;

            ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
            layoutParams.height = newHeightInPixels;
            scrollView.setLayoutParams(layoutParams);

            TextHolder.SetTextSize(screenHeight,TextView2,0.035);
            TextHolder.SetTextSize(screenHeight,TopText,0.035);
            TextHolder.SetTextSize(screenHeight,TextView14,0.035);
            TextHolder.SetTextSize(screenHeight,TextView15,0.035);
            TextHolder.SetTextSize(screenHeight,TextView16,0.035);
        }


        nav_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstView();
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
                toMain();
            }
        });

    }

    void toMain() {
        Intent intent = new Intent(this, VerificationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }

    void toProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    void toFirstView(){
        Intent intent = new Intent(this,FirstScreen.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }

    void toLogin() {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
}