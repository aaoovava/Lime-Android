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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.limeapp.R;
import com.example.limeapp.core.GfreezeDrop;
import com.example.limeapp.core.VerificationActivity;
import com.example.limeapp.core.enums.AbonimentGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
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
        TextView DateTextView = view.findViewById(R.id.textView28);
        if (isFreezing) {
            DateTextView.setText("Твій абонемент буде неактивний "+ daysOfFreezing + " днів");
        }
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFreezing) {
                    if (abonimentGroup == AbonimentGroup.PERSONAL) {
                        personalAbonimentFreezing();
                        returnToVerification();
                    } else if (abonimentGroup == AbonimentGroup.GROUP) {
                        groupAbonimentFreezing();
                        returnToVerification();
                    }
                }else {
                    if (abonimentGroup == AbonimentGroup.PERSONAL) {
                        persnalAbonimentDropFreezing();
                        returnToVerification();
                    }
                    else if (abonimentGroup == AbonimentGroup.GROUP) {
                        groupAbonimetnDropFreezing();
                        returnToVerification();
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
        doneDialog();
        dismiss();
    }

    private void doneDialog() {
        layout = R.layout.done_freezing;
    }

    private void personalAbonimentFreezing() {
        TextView DateTextView = getView().findViewById(R.id.textView28);
        DateTextView.setText("Твій абонемент буде неактивний "+ daysOfFreezing + " днів");

        HashMap<String, Object> j = new HashMap<>();
        j.put("aboniment_end_date",newAbonimentEndDate);
        j.put("aboniment_status","2");
        j.put("afreeze_days", daysOfFreezing);
        j.put("afreeze_date", aFreezeDate);
        users.child(userId).updateChildren(j);

    }
    private void groupAbonimentFreezing() {
        HashMap<String ,Object> j = new HashMap<>();
        j.put("group_t_end_date",newAbonimentEndDate);
        j.put("group_t_status","2");
        j.put("gfreeze_days", "0");
        j.put("gfreeze_date", aFreezeDate);
        users.child(userId).updateChildren(j);
    }

    private void persnalAbonimentDropFreezing() {
        HashMap<String, Object> j = new HashMap<>();
        j.put("aboniment_end_date",newAbonimentEndDate);
        j.put("aboniment_status", "1");
        j.put("afreeze_days", "0");
        users.child(userId).updateChildren(j);

    }
    private void groupAbonimetnDropFreezing() {
        HashMap<String, Object> j = new HashMap<>();
        j.put("group_t_end_date",newAbonimentEndDate);
        j.put("group_t_status", "1");
        j.put("gfreeze_days", "0");
        users.child(userId).updateChildren(j);
    }

}
