package com.example.limeapp.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.R;
import com.example.limeapp.ob_class.TimeTableItem;

import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.Holder> {
    private Context context;
    private ArrayList<TimeTableItem>list;

    public TimeTableAdapter(Context context, ArrayList<TimeTableItem> list) {
        this.context = context;
        this.list = list;
    }
    public void setTable(ArrayList<TimeTableItem>list){
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.timetable_item,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        TimeTableItem timeTableItem = list.get(position);
        holder.Name.setText(timeTableItem.getName());
        holder.Owner.setText(timeTableItem.getOwner());
        holder.Time1.setText(timeTableItem.getTime1());
        holder.Time2.setText(timeTableItem.getTime2());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class Holder extends RecyclerView.ViewHolder{
        TextView Name, Owner, Time1, Time2;
        public Holder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Owner = itemView.findViewById(R.id.Owner);
            Time1 = itemView.findViewById(R.id.time1);
            Time2 = itemView.findViewById(R.id.time2);
        }
    }
}
