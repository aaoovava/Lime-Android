package com.example.limeapp.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.core.interfaces.listeners.OnDateClickListener;
import com.example.limeapp.R;
import com.example.limeapp.ob_class.DateItem;

import java.util.ArrayList;

public class TimeTableDatesAdapter extends RecyclerView.Adapter<TimeTableDatesAdapter.Holder> {
    private Context context;
    private ArrayList<DateItem>list;
    private OnDateClickListener listener;
    private int selectedPosition = 0;
    private boolean isFirst = true;

    public void setListener(OnDateClickListener listener) {
        this.listener = listener;
    }

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
        if (isFirst) {
            listener.onItemClick(dateItem);
            isFirst = false;
        }


        holder.SelectBut.setVisibility(selectedPosition == position ? View.VISIBLE : View.INVISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                listener.onItemClick(dateItem);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private TextView Date, Day;
        private ImageView SelectBut;
        public Holder(@NonNull View itemView) {
            super(itemView);
            Date = itemView.findViewById(R.id.date);
            Day = itemView.findViewById(R.id.day);
            SelectBut = itemView.findViewById(R.id.isPick);
        }
    }
}
