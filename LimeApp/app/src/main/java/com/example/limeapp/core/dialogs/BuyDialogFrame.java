package com.example.limeapp.core.dialogs;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.core.adapters.BuyAdapter;
import com.example.limeapp.core.enums.AbonimentGroup;
import com.example.limeapp.core.interfaces.listeners.OnSaleItemClickListener;
import com.example.limeapp.R;
import com.example.limeapp.ob_class.SalesItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyDialogFrame extends DialogFragment implements OnSaleItemClickListener {
    private AbonimentGroup abonimentGroup;
    private ImageView CloseButton, AcceptButton, CardBack;
    private RecyclerView recyclerView;
    private ArrayList<SalesItem>list;
    private TextView PayValue;
    private FirebaseDatabase db;
    private BuyAdapter buyAdapter;
    private ArrayList<SalesItem>SaleList = new ArrayList<>();
    private int SaleValue = 0;

    public BuyDialogFrame(AbonimentGroup abonimentGroup) {
        this.abonimentGroup = abonimentGroup;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_price_activity, container, false);
        CloseButton = view.findViewById(R.id.closeButton);
        AcceptButton = view.findViewById(R.id.accept);
        PayValue = view.findViewById(R.id.value);
        recyclerView = view.findViewById(R.id.recyclerView);
        ConstraintLayout constraintLayout = view.findViewById(R.id.con2);
        CardBack = view.findViewById(R.id.imageView24);
        db = FirebaseDatabase.getInstance();


        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
        ViewGroup.LayoutParams cardLayoutParams = CardBack.getLayoutParams();

        layoutParams.width = cardLayoutParams.width;

        recyclerView.setLayoutParams(layoutParams);


        DatabaseReference groupSalesRef = db.getReference("GroupSales");
        DatabaseReference personalSalesRef = db.getReference("PersonalSales");
        if (abonimentGroup == AbonimentGroup.PERSONAL){
            personalSalesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list = new ArrayList<>();
                    buyAdapter = new BuyAdapter(view.getContext(), list);
                    buyAdapter.setListener(BuyDialogFrame.this);
                    recyclerView.setAdapter(buyAdapter);

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        SalesItem salesItem = dataSnapshot.getValue(SalesItem.class);
                        list.add(salesItem);
                    }
                    buyAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if (abonimentGroup == AbonimentGroup.GROUP){
            groupSalesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list = new ArrayList<>();
                    buyAdapter = new BuyAdapter(view.getContext(), list);
                    buyAdapter.setListener(BuyDialogFrame.this);
                    recyclerView.setAdapter(buyAdapter);
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        SalesItem salesItem = dataSnapshot.getValue(SalesItem.class);
                        list.add(salesItem);
                    }
                    buyAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        constraintLayout.setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.TRANSPARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    @Override
    public void OnSaleItemClick(SalesItem salesItem) {
        PayValue.setText(salesItem.getPrise() + " грн");
    }
}
