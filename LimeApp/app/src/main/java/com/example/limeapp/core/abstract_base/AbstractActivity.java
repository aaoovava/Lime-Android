package com.example.limeapp.core.abstract_base;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.limeapp.R;
import com.example.limeapp.core.activities.AbonimentFreezeActivity;
import com.example.limeapp.core.activities.BuyActivity;
import com.example.limeapp.core.activities.FirstScreenActivity;
import com.example.limeapp.core.activities.LoginActivity;
import com.example.limeapp.core.activities.ProfileActivity;
import com.example.limeapp.core.activities.RegistrationActivity;
import com.example.limeapp.core.activities.VerificationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class AbstractActivity extends AppCompatActivity {
   public FirebaseAuth auth = FirebaseAuth.getInstance();
   public FirebaseUser user = auth.getCurrentUser();
   public FirebaseDatabase db = FirebaseDatabase.getInstance();
   public String userId = user.getUid();
   public DatabaseReference users = db.getReference("Client");
   public DatabaseReference buyHistory = db.getReference("Client/" + userId +"/user_buys");
    public void toVerification() {
       Intent intent = new Intent(this, VerificationActivity.class);
       startActivity(intent);
       overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
   }
    public void toFirstScreen() {
        Intent intent = new Intent(this, FirstScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    public void toBuy() {
        Intent intent = new Intent(this, BuyActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    public void toProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    public void toAbonimentFreeze() {
        Intent intent = new Intent(this, AbonimentFreezeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    public void toGroupFreeze() {
        Intent intent = new Intent(this, VerificationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    public void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    public void toRegistration(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    public String[] Spase(String txt) {
        String[] words = String.format(txt).split(" ");
        return words;
    }
    public static String getCurrentDateFormatted() {

        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

        Locale locale = Locale.ENGLISH;
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("d MMMM, EEEE", locale);
        }
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = currentDate.format(formatter);
        }
        return formattedDate;
    }
    public void logOut() {
        auth.signOut();
        toLogin();
    }

}
