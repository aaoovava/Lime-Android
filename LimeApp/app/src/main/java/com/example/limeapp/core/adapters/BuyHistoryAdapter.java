package com.example.limeapp.core.adapters;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.holder.TextHolder;
import com.example.limeapp.R;
import com.example.limeapp.ob_class.UserBuys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class BuyHistoryAdapter extends RecyclerView.Adapter<BuyHistoryAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserBuys>list;

    public BuyHistoryAdapter(Context context, ArrayList<UserBuys> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.buy_history_item,parent,false);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DisplayMetrics displayMetrics2 = context.getResources().getDisplayMetrics();
        int screenWidthDp = (int) (displayMetrics2.widthPixels / displayMetrics2.density);
        int screenHeightDp = (int) (displayMetrics2.heightPixels / displayMetrics2.density);
        UserBuys user_buys = list.get(position);
        holder.Name.setText(user_buys.getName_of_buy());
        holder.Date.setText(convertToUkrainianFormat(user_buys.getDate()));
        holder.Prise.setText("- " + user_buys.getCost() + "грн");
        TextHolder.SetTextSize(screenHeightDp, holder.Name,0.08);
        TextHolder.SetTextSize(screenHeightDp,holder.Prise,0.08);
        TextHolder.SetTextSize(screenHeightDp,holder.Date,0.06);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Prise,Date;
        ImageView Solid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.textView13);
            Prise = itemView.findViewById(R.id.textView18);
            Date = itemView.findViewById(R.id.textView19);
            Solid = itemView.findViewById(R.id.imageView15);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertToUkrainianFormat(String inputDate) {
        try {
            // Преобразование строки в объект LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(inputDate, formatter);

            // Отформатировать дату с использованием украинского локализированного месяца
            DateTimeFormatter ukrainianFormatter = DateTimeFormatter.ofPattern("d MMM", new Locale("uk"));
            return date.format(ukrainianFormatter);
        } catch (Exception e) {
            e.printStackTrace(); // Обработка ошибок при некорректном формате даты
            return "Ошибка при преобразовании даты";
        }
    }

}
