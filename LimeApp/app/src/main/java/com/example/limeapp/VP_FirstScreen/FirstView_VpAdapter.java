package com.example.limeapp.VP_FirstScreen;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.R;

import java.util.ArrayList;

public class FirstView_VpAdapter extends RecyclerView.Adapter<FirstView_VpAdapter.ViewHolder> {
    public FirstView_VpAdapter(ArrayList<FirstViewItem> viewItemArrayList, Context context) {
        this.viewItemArrayList = viewItemArrayList;
        this.context = context;
        notifyDataSetChanged();
    }

    ArrayList<FirstViewItem> viewItemArrayList;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_first_screen_item_small,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirstViewItem firstViewItem = viewItemArrayList.get(position);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        if (windowManager != null && screenWidth >= 600) {


            //Txt %
            int textSize_yurname = (int) (screenHeight * 0.03);
            int txtSize_date = (int) (screenHeight * 0.025);
            int textSize_status = (int) (screenHeight * 0.025);
            int textSize_title = (int) (screenWidth * 0.06);
            int textSize_name = (int) (screenHeight * 0.032);

            holder.MainTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_title);
        }

        holder.CardView.setImageResource(firstViewItem.CardElement);
        holder.MainTextView.setText(firstViewItem.MainTxt);

    }

    @Override
    public int getItemCount() {
        return viewItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView MainTextView;
        ImageView CardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MainTextView = itemView.findViewById(R.id.MainText);
            CardView = itemView.findViewById(R.id.CardElement);
        }

    }
}
