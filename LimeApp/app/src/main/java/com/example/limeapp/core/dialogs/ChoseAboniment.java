package com.example.limeapp.core.dialogs;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.limeapp.R;
import com.example.limeapp.core.AFreeze;
import com.example.limeapp.core.AfreezeDrop;
import com.example.limeapp.core.GFreeze;
import com.example.limeapp.core.GfreezeDrop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Duration;


public class ChoseAboniment extends DialogFragment {
    ImageView abonimentButton, groupAbonimetnButton;
    FirebaseDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chose_aboniment_freezing, container, false);
        abonimentButton = view.findViewById(R.id.abonimentButton);
        groupAbonimetnButton = view.findViewById(R.id.groupAbonimentButton);

        TextView personalAboniment = view.findViewById(R.id.personalAboniment);
        TextView groupAboniment = view.findViewById(R.id.groupAboniment) ;

        DatabaseReference users = FirebaseDatabase.getInstance().getReference("Client");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();

        abonimentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalAbonimetnButtonHandler(personalAboniment, users, userId);
            }
        });

        groupAbonimetnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupAbonimetnButtonHandler(groupAboniment, users, userId);
            }
        });

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }
    private void groupAbonimetnButtonHandler(TextView text, DatabaseReference users, String userId){
        users.child(userId).addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String status = snapshot.child("group_t_status").getValue().toString();
                    String freezing = snapshot.child("gfreeze_days").getValue().toString();
                    if (freezing.equals("0")) {
                        Toast toast = new Toast(getContext());
                        toast.setText("Функція заморозки вичерпана");
                        toast.show();
                    } else {
                        Handler handler = new Handler();
                        switch (status) {
                            case "1":
                                text.setTextColor(ContextCompat.getColor(getContext(), R.color.Status1));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toGfreeze();
                                        dismiss();
                                    }
                                }, 100);
                                break;
                            case "2":
                                text.setTextColor(ContextCompat.getColor(getContext(), R.color.Status1));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toGfreezeDrop();
                                        dismiss();
                                    }
                                }, 100);
                                break;
                            default:
                                Toast toast = new Toast(getContext());
                                toast.setText("Відсутній абонемент");
                                toast.show();
                        }
                    }
                }catch (NullPointerException e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void personalAbonimetnButtonHandler(TextView text, DatabaseReference users, String userId){
        users.child(userId).addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String status = snapshot.child("aboniment_status").getValue().toString();
                    String freezing = snapshot.child("afreeze_days").getValue().toString();
                    if (freezing.equals("0")) {
                        Toast toast = new Toast(getContext());
                        toast.setText("Функція заморозки вичерпана");
                        toast.show();
                    } else {
                        Handler handler = new Handler();
                        switch (status) {
                            case "1":
                                text.setTextColor(ContextCompat.getColor(getContext(), R.color.Status1));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toAfreeze();
                                        dismiss();
                                    }
                                }, 100);
                                break;
                            case "2":
                                text.setTextColor(ContextCompat.getColor(getContext(), R.color.Status1));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toAfreezeDrop();
                                        dismiss();
                                    }
                                }, 100);
                                break;
                            default:
                                Toast toast = new Toast(getContext());
                                toast.setText("Відсутній абонемент");
                                toast.show();
                        }
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void toAfreeze(){
        Intent intent = new Intent(getContext(), AFreeze.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    private void toGfreeze(){
        Intent intent = new Intent(getContext(), GFreeze.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    private void toAfreezeDrop(){
        Intent intent = new Intent(getContext(), AfreezeDrop.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
    private void toGfreezeDrop(){
        Intent intent = new Intent(getContext(), GfreezeDrop.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }
}
