package com.example.limeapp.Core.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.R;
import com.example.limeapp.ob_class.DateItem;

import java.util.ArrayList;

public class TimeTableDatesAdapter extends RecyclerView.Adapter<TimeTableDatesAdapter.Holder> {
    private Context context;
    private ArrayList<DateItem>list;

    public TimeTableDatesAdapter(Context context, ArrayList<DateItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.date_item,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DateItem dateItem = list.get(position);
        holder.Date.setText(dateItem.getDate());
        holder.Day.setText(dateItem.getDay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private TextView Date, Day;
        public Holder(@NonNull View itemView) {
            super(itemView);
            Date = itemView.findViewById(R.id.date);
            Day = itemView.findViewById(R.id.day);
        }
    }
}
