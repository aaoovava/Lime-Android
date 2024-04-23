package com.example.limeapp.core.dialogs;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.limeapp.R;
import com.example.limeapp.core.activities.VerificationActivity;
import com.example.limeapp.core.enums.AbonimentGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ReaskDialog extends DialogFragment {
    int layout = R.layout.reask_freezing;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference users = db.getReference("Client");
    String userId = currentUser.getUid();
    private boolean isFreezing;
    private String newAbonimentEndDate;
    private String aFreezeDate;
    private int daysOfFreezing;
    private AbonimentGroup abonimentGroup;
    private ArrayList<View> listOfAsking = new ArrayList<>();



    /**
     *
     * @param isFreezing
     * @param newAbonimentEndDate
     * @param aFreezeDate
     * @param daysOfFreezing
     * @param abonimentGroup
     */
   public ReaskDialog(boolean isFreezing, String newAbonimentEndDate, String aFreezeDate, int daysOfFreezing, AbonimentGroup abonimentGroup) {
        this.isFreezing = isFreezing;
        this.newAbonimentEndDate = newAbonimentEndDate;
        this.aFreezeDate = aFreezeDate;
        this.daysOfFreezing = daysOfFreezing;
        this.abonimentGroup = abonimentGroup;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layout, container, false);
        ImageView yes = view.findViewById(R.id.Yes);
        ImageView no = view.findViewById(R.id.No);
        TextView dateTextView = view.findViewById(R.id.textView28);
        TextView title = view.findViewById(R.id.textView11);
        TextView textYes = view.findViewById(R.id.textView30);
        TextView textNo = view.findViewById(R.id.textView31);
        TextView bottomText = view.findViewById(R.id.textView28);
        listOfAsking.add(title);
        listOfAsking.add(textYes);
        listOfAsking.add(textNo);
        listOfAsking.add(bottomText);
        listOfAsking.add(yes);
        listOfAsking.add(no);

        if (isFreezing) {
            dateTextView.setText("Твій абонемент буде неактивний "+ daysOfFreezing + " днів");
        }
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFreezing) {
                    if (abonimentGroup == AbonimentGroup.PERSONAL) {
                        returnToVerification();
                        personalAbonimentFreezing();
                    } else if (abonimentGroup == AbonimentGroup.GROUP) {
                        returnToVerification();
                        groupAbonimentFreezing();
                    }
                }else {
                    if (abonimentGroup == AbonimentGroup.PERSONAL) {
                        returnToVerification();
                        persnalAbonimentDropFreezing();
                    }
                    else if (abonimentGroup == AbonimentGroup.GROUP) {
                        returnToVerification();
                        groupAbonimetnDropFreezing();
                    }
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;

    }

    private void returnToVerification() {
        done();
    }


    private void personalAbonimentFreezing() {
       try {
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   TextView DateTextView = getView().findViewById(R.id.textView28);
                   DateTextView.setText("Твій абонемент буде неактивний "+ daysOfFreezing + " днів");

                   HashMap<String, Object> j = new HashMap<>();
                   j.put("aboniment_end_date",newAbonimentEndDate);
                   j.put("aboniment_status","2");
                   j.put("afreeze_date", aFreezeDate);
                   users.child(userId).updateChildren(j);
               }
           }, 1000);


       }catch (Exception e){
       }
    }
    private void groupAbonimentFreezing() {
       try {
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   HashMap<String ,Object> j = new HashMap<>();
                   j.put("group_t_end_date",newAbonimentEndDate);
                   j.put("group_t_status","2");
                   j.put("gfreeze_date", aFreezeDate);
                   users.child(userId).updateChildren(j);
               }
           }, 1000);

       }catch (Exception e){
       }
    }

    private void persnalAbonimentDropFreezing() {
       try {
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   HashMap<String, Object> j = new HashMap<>();
                   j.put("aboniment_end_date", newAbonimentEndDate);
                   j.put("aboniment_status", "1");
                   j.put("afreeze_days", "0");
                   users.child(userId).updateChildren(j);
               }
           }, 1000);
       }catch (Exception e){

       }

    }
    private void groupAbonimetnDropFreezing() {
       try {
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   HashMap<String, Object> j = new HashMap<>();
                   j.put("group_t_end_date", newAbonimentEndDate);
                   j.put("group_t_status", "1");
                   j.put("gfreeze_days", "0");
                   users.child(userId).updateChildren(j);
               }
           }, 1000);

       }catch (Exception e){
           System.out.printf("sd");
       }
    }
    private void toFirstScreen() {
        Intent intent = new Intent(getContext(), VerificationActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    private void done() {
       for (View v : listOfAsking) v.setVisibility(View.INVISIBLE);
       getView().findViewById(R.id.textView29).setVisibility(View.VISIBLE);
       toFirstScreen();
    }
}
