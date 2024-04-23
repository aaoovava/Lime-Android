package com.example.limeapp.core.activities;import android.app.Dialog;import android.content.Intent;import android.graphics.drawable.ColorDrawable;import android.graphics.drawable.Drawable;import android.net.Uri;import android.os.Build;import android.os.Bundle;import android.view.View;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.widget.ImageView;import android.widget.ProgressBar;import androidx.annotation.NonNull;import androidx.annotation.RequiresApi;import androidx.appcompat.app.AppCompatDelegate;import androidx.core.content.ContextCompat;import androidx.viewpager2.widget.ViewPager2;import com.example.limeapp.core.abstract_base.AbstractActivity;import com.example.limeapp.core.adapters.VP_Adapter;import com.example.limeapp.R;import com.example.limeapp.ob_class.ViewPagerItem;import com.google.android.material.floatingactionbutton.FloatingActionButton;import com.google.firebase.auth.FirebaseAuth;import com.google.firebase.auth.FirebaseUser;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;import java.text.DateFormat;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.ArrayList;import java.util.Date;import java.util.HashMap;public class VerificationActivity extends AbstractActivity {    ArrayList<ViewPagerItem> viePagerItemArrayList;    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.main_screen_activity);        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));        ImageView nav_profile = findViewById(R.id.nav_profile);        ImageView nav_buy = findViewById(R.id.nav_buy);        ImageView nav_firstScreen = findViewById(R.id.nav_firstScreen);        ViewPager2 viewPager2 = findViewById(R.id.viewPager);        SpringDotsIndicator dotsIndicator = findViewById(R.id.Adapter);        ProgressBar progressBar4 = findViewById(R.id.progressBar4);        ImageView MainInfoBut1 = findViewById(R.id.imageView23);        ImageView MainInfoBut2 = findViewById(R.id.imageView14);        FirebaseAuth auth = FirebaseAuth.getInstance();        FirebaseUser user = auth.getCurrentUser();        db = FirebaseDatabase.getInstance();        DatabaseReference users = db.getReference("Client");        String userId = user.getUid();        try {            if (user == null) {                toLogin();            } else {                progressBar4.setVisibility(View.VISIBLE);                MainInfoBut1.setOnClickListener(new View.OnClickListener() {                    @Override                    public void onClick(View v) {                        Dialog dialog = new Dialog(VerificationActivity.this);                        dialog.setContentView(R.layout.activity_main_info);                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));                        ImageView closeButton = dialog.findViewById(R.id.imageView39);                        dialog.show();                        ImageView mapBut = dialog.findViewById(R.id.imageView43);                        ImageView instBut = dialog.findViewById(R.id.imageView50);                        ImageView faceBut = dialog.findViewById(R.id.imageView51);                        FloatingActionButton floatingActionButton = dialog.findViewById(R.id.floatingActionButton);                        FloatingActionButton floatingActionButton2 = dialog.findViewById(R.id.floatingActionButton2);                        ImageView num1 = dialog.findViewById(R.id.imageView48);                        ImageView num2 = dialog.findViewById(R.id.imageView49);                        num2.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                String dial = "tel:" + "0953836588";                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));                            }                        });                        num1.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                String dial = "tel:" + "0535556530";                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));                            }                        });                        floatingActionButton2.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                floatingActionButton2.setVisibility(View.INVISIBLE);                                instBut.setVisibility(View.VISIBLE);                                faceBut.setVisibility(View.VISIBLE);                                Animation fadeInAnimation = AnimationUtils.loadAnimation(VerificationActivity.this, android.R.anim.fade_in);                                Animation fadeOutAnimation1 = AnimationUtils.loadAnimation(VerificationActivity.this, android.R.anim.slide_out_right);                                instBut.startAnimation(fadeInAnimation);                                instBut.startAnimation(fadeInAnimation);                                floatingActionButton2.startAnimation(fadeOutAnimation1);                            }                        });                        faceBut.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/profile.php?id=100090329526191"));                                startActivity(browserIntent);                            }                        });                        instBut.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/lime_myrhorod?igshid=NTc4MTIwNjQ2YQ=="));                                startActivity(browserIntent);                            }                        });                        floatingActionButton.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                String dial = "tel:" + "0953836588";                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));                            }                        });                        mapBut.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/WFztc1xyYPfoGPHr6"));                                startActivity(browserIntent);                            }                        });                        closeButton.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                dialog.dismiss();                            }                        });                    }                });                MainInfoBut2.setOnClickListener(new View.OnClickListener() {                    @Override                    public void onClick(View v) {                        Dialog dialog = new Dialog(VerificationActivity.this);                        dialog.setContentView(R.layout.activity_main_info);                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));                        ImageView closeButton = dialog.findViewById(R.id.imageView39);                        dialog.show();                        ImageView mapBut = dialog.findViewById(R.id.imageView43);                        ImageView instBut = dialog.findViewById(R.id.imageView50);                        FloatingActionButton floatingActionButton2 = dialog.findViewById(R.id.floatingActionButton2);                        ImageView faceBut = dialog.findViewById(R.id.imageView51);                        FloatingActionButton floatingActionButton = dialog.findViewById(R.id.floatingActionButton);                        ImageView num1 = dialog.findViewById(R.id.imageView48);                        ImageView num2 = dialog.findViewById(R.id.imageView49);                        num2.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                String dial = "tel:" + "0953836588";                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));                            }                        });                        num1.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                String dial = "tel:" + "0535556530";                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));                            }                        });                        floatingActionButton2.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                floatingActionButton2.setVisibility(View.INVISIBLE);                                instBut.setVisibility(View.VISIBLE);                                faceBut.setVisibility(View.VISIBLE);                                Animation fadeInAnimation = AnimationUtils.loadAnimation(VerificationActivity.this, android.R.anim.fade_in);                                Animation fadeOutAnimation1 = AnimationUtils.loadAnimation(VerificationActivity.this, android.R.anim.slide_out_right);                                instBut.startAnimation(fadeInAnimation);                                instBut.startAnimation(fadeInAnimation);                                floatingActionButton2.startAnimation(fadeOutAnimation1);                            }                        });                        faceBut.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/profile.php?id=100090329526191"));                                startActivity(browserIntent);                            }                        });                        instBut.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/lime_myrhorod?igshid=NTc4MTIwNjQ2YQ=="));                                startActivity(browserIntent);                            }                        });                        floatingActionButton.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                String dial = "tel:" + "0953836588";                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));                            }                        });                        mapBut.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/WFztc1xyYPfoGPHr6"));                                startActivity(browserIntent);                            }                        });                        closeButton.setOnClickListener(new View.OnClickListener() {                            @Override                            public void onClick(View v) {                                dialog.dismiss();                            }                        });                    }                });                int data;                users.child(userId).addValueEventListener(new ValueEventListener() {                    @RequiresApi(api = Build.VERSION_CODES.O)                    @Override                    public void onDataChange(@NonNull DataSnapshot snapshot) {                        String Uname, Sname;                        try {                            String PName = snapshot.child("aboniment_status").getValue().toString();                        }catch (Exception e){                            toLogin();                        }                        String usName = snapshot.child("name").getValue().toString();                        String AfirstDate = snapshot.child("aboniment_start_date").getValue().toString();                        String ALastDate = snapshot.child("aboniment_end_date").getValue().toString();                        String PfirstDate = snapshot.child("group_t_start_date").getValue().toString();                        String PLastDate = snapshot.child("group_t_end_date").getValue().toString();                        String AFreezeDate = snapshot.child("afreeze_date").getValue().toString();                        String GFreezeDate = snapshot.child("gfreeze_date").getValue().toString();                        int Astatus = Integer.parseInt(snapshot.child("aboniment_status").getValue().toString());                        int Gstatus = Integer.parseInt(snapshot.child("group_t_status").getValue().toString());                        //Check status methods                        if (Astatus == 1) {                            Date Localdate = new Date();                            Date ALastDateD = setDate(ALastDate);                            int comparison = Localdate.compareTo(ALastDateD);                            if (comparison < 0) {                                HashMap<String, Object> j = new HashMap<>();                                j.put("aboniment_status", "1");                                users.child(auth.getCurrentUser().getUid()).updateChildren(j);                            } else if (comparison > 0) {                                HashMap<String, Object> j = new HashMap<>();                                j.put("aboniment_status", "3");                                j.put("aboniment_start_date", "--.--.----");                                j.put("aboniment_end_date", "--.--.----");                                users.child(auth.getCurrentUser().getUid()).updateChildren(j);                            }                        } else if (Astatus == 2) {                            try {                                Date LocalDate = new Date();                                Date AFreezeDateD = setDate(AFreezeDate);                                int comparison = LocalDate.compareTo(AFreezeDateD);                                if (comparison >= 0) {                                    HashMap<String, Object> j = new HashMap<>();                                    j.put("aboniment_status", "1");                                    users.child(auth.getCurrentUser().getUid()).updateChildren(j);                                } else {                                    HashMap<String, Object> j = new HashMap<>();                                    j.put("aboniment_status", "2");                                    users.child(auth.getCurrentUser().getUid()).updateChildren(j);                                }                            } catch (Exception e) {                            }                        }                        if (Gstatus == 1) {                            Date Localdate = new Date();                            Date GLastDateD = setDate(PLastDate);                            int comparison = Localdate.compareTo(GLastDateD);                            if (comparison < 0) {                                HashMap<String, Object> j = new HashMap<>();                                j.put("group_t_status", "1");                                users.child(auth.getCurrentUser().getUid()).updateChildren(j);                            } else if (comparison > 0) {                                HashMap<String, Object> j = new HashMap<>();                                j.put("group_t_status", "3");                                j.put("countOfGT", "0");                                j.put("group_t_start_date", "--.--.----");                                j.put("group_t_end_date", "--.--.----");                                users.child(auth.getCurrentUser().getUid()).updateChildren(j);                            }                        } else if (Gstatus == 2) {                            Date LocalDate = new Date();                            Date GFreezeDateD = setDate(GFreezeDate);                            try {                                int comparison = LocalDate.compareTo(GFreezeDateD);                                if (comparison >= 0) {                                    HashMap<String, Object> j = new HashMap<>();                                    j.put("group_t_status", "1");                                    users.child(auth.getCurrentUser().getUid()).updateChildren(j);                                } else {                                    HashMap<String, Object> j = new HashMap<>();                                    j.put("group_t_status", "2");                                    users.child(auth.getCurrentUser().getUid()).updateChildren(j);                                }                            } catch (Exception e) {                            }                        }                        try {                            String[] words = Spase(usName);                            Uname = words[0];                            Sname = words[1];                        } catch (Exception e) {                            Uname = usName;                            Sname = "";                        }                        viePagerItemArrayList = new ArrayList<>();                        String image = "";                        try {                            image = snapshot.child("image").getValue().toString();                        } catch (Exception e) {                            image = "https://firebasestorage.googleapis.com/v0/b/lime-pdb.appspot.com/o/user-profile-4255%201.png?alt=media&token=d95cec80-d997-4d83-8c6f-30c8e98f3edb";                        }                        ViewPagerItem AviePagerItem = new ViewPagerItem(Uname, Sname, "Абонемент", "Дата початку дії: ", "Дата закінчення дії: ", image, AfirstDate, ALastDate, "", getResources().getDrawable(R.drawable.freeze), getResources().getDrawable(R.drawable.rectangle_7oki), getStatus(Astatus), getStatusColor(Astatus), getStatusTxt(Astatus));                        viePagerItemArrayList.add(AviePagerItem);                        ViewPagerItem PviePagerItem = new ViewPagerItem(Uname, Sname, "Групові заняття", "Дата початку дії: ", "Дата закінчення дії: ", image, PfirstDate, PLastDate, snapshot.child("countOfGT").getValue().toString(), getResources().getDrawable(R.drawable.group_18), getResources().getDrawable(R.drawable.freeze), getStatus(Gstatus), getStatusColor(Gstatus), getStatusTxt(Gstatus));                        viePagerItemArrayList.add(PviePagerItem);                        VP_Adapter vp_adapter = new VP_Adapter(viePagerItemArrayList, VerificationActivity.this);                        viewPager2.setAdapter(vp_adapter);                        viewPager2.setClipToPadding(false);                        viewPager2.setClipChildren(false);                        viewPager2.setOffscreenPageLimit(2);                        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);                        dotsIndicator.attachTo(viewPager2);                        progressBar4.setVisibility(View.INVISIBLE);                        dotsIndicator.setVisibility(View.VISIBLE);                    }                    @Override                    public void onCancelled(@NonNull DatabaseError error) {                    }                });                nav_profile.setOnClickListener(new View.OnClickListener() {                    @Override                    public void onClick(View v) {                        toProfile();                    }                });                nav_buy.setOnClickListener(new View.OnClickListener() {                    @Override                    public void onClick(View v) {                        toBuy();                    }                });                nav_firstScreen.setOnClickListener(new View.OnClickListener() {                    @Override                    public void onClick(View v) {                        toFirstScreen();                    }                });            }        } catch (Exception e) {            toLogin();        }    }    public String[] Spase(String txt) {        String[] words = String.format(txt).split(" ");        return words;    }    Drawable getStatus(int status) {        Drawable res = null;        switch (status) {            case 1:                res = getResources().getDrawable(R.drawable.cardone);                break;            case 2:                res = getResources().getDrawable(R.drawable.cardone);                break;            case 3:                res = getResources().getDrawable(R.drawable.cardone);                break;        }        return res;    }    int getStatusColor(int status) {        int res = 1;        switch (status) {            case 1:                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                    res = ContextCompat.getColor(VerificationActivity.this, R.color.Status1);                }                break;            case 2:                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                    res = ContextCompat.getColor(VerificationActivity.this, R.color.Status2);                }                break;            case 3:                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                    res = ContextCompat.getColor(VerificationActivity.this, R.color.Status3);                }                break;        }        return res;    }    String getStatusTxt(int status) {        String res = null;        switch (status) {            case 1:                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                    res = "Активний";                }                break;            case 2:                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                    res = "Заморожений";                }                break;            case 3:                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {                    res = "Неактивний";                }                break;        }        return res;    }    public Date setDate(String data) {        String dateString = data;        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");        Date date = null;        try {            date = format.parse(dateString);            System.out.println(date);        } catch (ParseException e) {            e.printStackTrace();        }        return date;    }}