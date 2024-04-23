package com.example.limeapp.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.core.interfaces.listeners.OnSaleItemClickListener;
import com.example.limeapp.R;
import com.example.limeapp.ob_class.SalesItem;

import java.util.ArrayList;

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SalesItem>list;
    private OnSaleItemClickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public void setListener(OnSaleItemClickListener listener) {
        this.listener = listener;
    }

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

        holder.TabView.setImageResource(selectedPosition == position ? R.drawable.buytabclicked : R.drawable.prise_frame);
        holder.Name.setTextColor(ContextCompat.getColor(context, selectedPosition == position ? R.color.white : R.color.Status1));
        holder.Prise.setTextColor(ContextCompat.getColor(context, selectedPosition == position ? R.color.white : R.color.Status1));

        holder.TabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
                if (listener != null) listener.OnSaleItemClick(salesItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Prise, Description;
        ImageView TabView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Prise = itemView.findViewById(R.id.prise);
            Description = itemView.findViewById(R.id.description);
            TabView = itemView.findViewById(R.id.BuyImage);
        }
    }
    public String getPrise(String s){
        String[]split = s.split(" ");
        return split[0];
    }
}
