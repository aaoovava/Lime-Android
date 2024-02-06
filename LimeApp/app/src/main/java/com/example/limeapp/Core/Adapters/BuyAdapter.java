package com.example.limeapp.Core.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.R;
import com.example.limeapp.ob_class.SalesItem;

import java.util.ArrayList;

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.MyViewHolder> {
    Context context;
    ArrayList<SalesItem>list;

    public BuyAdapter(Context context, ArrayList<SalesItem>list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.buy_price_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SalesItem salesItem = list.get(position);
        holder.Name.setText(salesItem.getName());
        holder.Description.setText(salesItem.getDescription());
        holder.Prise.setText(salesItem.getPrise() + " грн");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Prise, Description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Prise = itemView.findViewById(R.id.prise);
            Description = itemView.findViewById(R.id.description);
        }
    }
}
