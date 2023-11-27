package com.example.limeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BuyScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_activity);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));

        ImageView nav_verefication = findViewById(R.id.nav_verefication);
        ImageView nav_profil = findViewById(R.id.nav_profile);

        ImageView MainInfoBut1 = findViewById(R.id.imageView28);
        ImageView MainInfoBut2 = findViewById(R.id.imageView23);
        MainInfoBut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(BuyScreen.this);
                dialog.setContentView(R.layout.activity_main_info);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                ImageView closeButton = dialog.findViewById(R.id.imageView39);
                dialog.show();

                ImageView mapBut = dialog.findViewById(R.id.imageView43);
                ImageView instBut = dialog.findViewById(R.id.imageView50);
                FloatingActionButton floatingActionButton2 = dialog.findViewById(R.id.floatingActionButton2);
                ImageView faceBut = dialog.findViewById(R.id.imageView51);
                FloatingActionButton floatingActionButton = dialog.findViewById(R.id.floatingActionButton);


                ImageView num1 = dialog.findViewById(R.id.imageView48);
                ImageView num2 = dialog.findViewById(R.id.imageView49);
                num2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + "0953836588";
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });
                num1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + "0535556530";
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });
                floatingActionButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        floatingActionButton2.setVisibility(View.INVISIBLE);
                        instBut.setVisibility(View.VISIBLE);
                        faceBut.setVisibility(View.VISIBLE);
                        Animation fadeInAnimation = AnimationUtils.loadAnimation(BuyScreen.this, android.R.anim.fade_in);
                        Animation fadeOutAnimation1 = AnimationUtils.loadAnimation(BuyScreen.this, android.R.anim.slide_out_right);
                        instBut.startAnimation(fadeInAnimation);
                        instBut.startAnimation(fadeInAnimation);
                        floatingActionButton2.startAnimation(fadeOutAnimation1);
                    }
                });

                faceBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/profile.php?id=100090329526191"));
                        startActivity(browserIntent);
                    }
                });
                instBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/lime_myrhorod?igshid=NTc4MTIwNjQ2YQ=="));
                        startActivity(browserIntent);
                    }
                });
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + "0953836588";
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });
                mapBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/WFztc1xyYPfoGPHr6"));
                        startActivity(browserIntent);
                    }
                });


                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        MainInfoBut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(BuyScreen.this);
                dialog.setContentView(R.layout.activity_main_info);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                ImageView closeButton = dialog.findViewById(R.id.imageView39);
                dialog.show();

                ImageView mapBut = dialog.findViewById(R.id.imageView43);
                ImageView instBut = dialog.findViewById(R.id.imageView50);
                ImageView faceBut = dialog.findViewById(R.id.imageView51);
                FloatingActionButton floatingActionButton = dialog.findViewById(R.id.floatingActionButton);
                FloatingActionButton floatingActionButton2 = dialog.findViewById(R.id.floatingActionButton2);
                ImageView num1 = dialog.findViewById(R.id.imageView48);
                ImageView num2 = dialog.findViewById(R.id.imageView49);
                num2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + "0953836588";
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });
                num1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + "0535556530";
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });
                floatingActionButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        floatingActionButton2.setVisibility(View.INVISIBLE);
                        instBut.setVisibility(View.VISIBLE);
                        faceBut.setVisibility(View.VISIBLE);
                        Animation fadeInAnimation = AnimationUtils.loadAnimation(BuyScreen.this, android.R.anim.fade_in);
                        Animation fadeOutAnimation1 = AnimationUtils.loadAnimation(BuyScreen.this, android.R.anim.slide_out_right);
                        instBut.startAnimation(fadeInAnimation);
                        instBut.startAnimation(fadeInAnimation);
                        floatingActionButton2.startAnimation(fadeOutAnimation1);
                    }
                });

                faceBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/profile.php?id=100090329526191"));
                        startActivity(browserIntent);
                    }
                });
                instBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/lime_myrhorod?igshid=NTc4MTIwNjQ2YQ=="));
                        startActivity(browserIntent);
                    }
                });
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dial = "tel:" + "0953836588";
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }
                });
                mapBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/WFztc1xyYPfoGPHr6"));
                        startActivity(browserIntent);
                    }
                });


                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
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

    void toLogin() {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
}