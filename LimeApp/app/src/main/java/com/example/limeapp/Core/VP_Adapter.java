package com.example.limeapp.Core;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VP_Adapter extends RecyclerView.Adapter<VP_Adapter.ViewHolder> {
    FirebaseDatabase db;
    private List<ViePagerItem> viePagerItemsArrayList;
    private Context context;


    public VP_Adapter(ArrayList<ViePagerItem> viePagerItemsArrayList, Context context) {
        this.viePagerItemsArrayList = viePagerItemsArrayList;
        this.context = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference("Client");
        String userId = user.getUid();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        users.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String AfirstDate = snapshot.child("aboniment_start_date").getValue().toString();
                String ALastDate = snapshot.child("aboniment_end_date").getValue().toString();
                int Astatus = Integer.parseInt(snapshot.child("aboniment_status").getValue().toString());
                int Gstatus = Integer.parseInt(snapshot.child("group_t_status").getValue().toString());
                int Acount = Integer.parseInt(snapshot.child("afreeze_days").getValue().toString());
                int Gcount = Integer.parseInt(snapshot.child("gfreeze_days").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViePagerItem pagerItem = viePagerItemsArrayList.get(position);
        try {
            Picasso.get().load(pagerItem.Pimg).into(holder.circleImageView);
            holder.circleImageView.setVisibility(View.VISIBLE);
            holder.dflImage.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            holder.circleImageView.setVisibility(View.INVISIBLE);
            holder.dflImage.setVisibility(View.VISIBLE);
        }
        //Image%


        // %
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //Txt %
            int screenHeight = displayMetrics.heightPixels;
            int screenWidth = displayMetrics.widthPixels;
            int textSize_yurname = (int) (screenHeight * 0.03);
            int txtSize_date = (int) (screenHeight * 0.025);
            int textSize_status = (int) (screenHeight * 0.025);
            int textSize_title = (int) (screenHeight * 0.04);
            int textSize_name = (int) (screenHeight * 0.032);
            // %margin of items
            //%Text
            ViewGroup.MarginLayoutParams layoutParams_YoreName = (ViewGroup.MarginLayoutParams) holder.YurName.getLayoutParams();
            int newMarginLeft_Y = (int) (screenWidth * 0.08);
            layoutParams_YoreName.setMargins(newMarginLeft_Y, layoutParams_YoreName.topMargin, layoutParams_YoreName.rightMargin, layoutParams_YoreName.bottomMargin);
            holder.YurName.setLayoutParams(layoutParams_YoreName);
            holder.YurName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_yurname);
            ViewGroup.MarginLayoutParams layoutParams_Txt = (ViewGroup.MarginLayoutParams) holder.UName.getLayoutParams();
            ViewGroup.MarginLayoutParams layoutParams_Txt1 = (ViewGroup.MarginLayoutParams) holder.SName.getLayoutParams();
            ViewGroup.MarginLayoutParams layoutParams_TxtStart_Date = (ViewGroup.MarginLayoutParams) holder.Start_date.getLayoutParams();
            ViewGroup.MarginLayoutParams layoutParams_Line = (ViewGroup.MarginLayoutParams) holder.Line.getLayoutParams();
            ViewGroup.MarginLayoutParams layoutParams_TxtStartDate = (ViewGroup.MarginLayoutParams) holder.StartDate.getLayoutParams();
            ViewGroup.MarginLayoutParams layoutParams_TxtEnd_Date = (ViewGroup.MarginLayoutParams) holder.End_date.getLayoutParams();
            ViewGroup.MarginLayoutParams layoutParams_TxtEndDate = (ViewGroup.MarginLayoutParams) holder.EndDate.getLayoutParams();


            int newMarginLeft_Txt = (int) (screenWidth * 0.08);
            int newMarginBotom_Line = screenHeight;
            int newMarginLeftAndRight_Line = screenWidth;
            int newMarginBotom_Txt = screenHeight;
            int newMarginBotomSatrtDate_Txt = screenHeight;
            int newMarginBotomEndDate_Txt = screenHeight;
            int newMarginBotomEnd_date_Txt = screenHeight;
            //small
            if (screenHeight < 2500 && screenHeight > 1500 && screenWidth >= 1080) {
                /* 2 */
                newMarginBotom_Txt = (int) (newMarginBotom_Txt * 0.01);
                /* 1 */
                newMarginBotom_Line = (int) (newMarginBotom_Line * 0.14);
                newMarginLeftAndRight_Line = (int) (newMarginLeftAndRight_Line * 0.08);
                /* 3 */
                newMarginBotomSatrtDate_Txt = (int) (newMarginBotomSatrtDate_Txt * 0.01);
                /* 3 */
                newMarginBotomSatrtDate_Txt = (int) (newMarginBotomSatrtDate_Txt * 0.01);
                /* 4 */
                newMarginBotomEnd_date_Txt = (int) (newMarginBotomEnd_date_Txt * 0.01);
                /* 5 */
                newMarginBotomEndDate_Txt = (int) (newMarginBotomEndDate_Txt * 0.05);
            }
            //big
            else if (screenHeight < 1500) {
                newMarginBotom_Txt = (int) (newMarginBotom_Txt * 0.01);
                newMarginBotom_Line = (int) (newMarginBotom_Line * 0.14);
                newMarginLeftAndRight_Line = (int) (newMarginLeftAndRight_Line * 0.08);
                /* 3 */
                newMarginBotomSatrtDate_Txt = (int) (newMarginBotomSatrtDate_Txt * 0.01);
                /* 4 */
                newMarginBotomEnd_date_Txt = (int) (newMarginBotomEnd_date_Txt * 0.01);
                /* 5 */
                newMarginBotomEndDate_Txt = (int) (newMarginBotomEndDate_Txt * 0.07);

            }
            //middle
            else {
                newMarginBotom_Txt = (int) (newMarginBotom_Txt * 0.01);
                newMarginBotom_Line = (int) (newMarginBotom_Line * 0.15);
                newMarginLeftAndRight_Line = (int) (newMarginLeftAndRight_Line * 0.085);
                /* 3 */
                newMarginBotomSatrtDate_Txt = (int) (newMarginBotomSatrtDate_Txt * 0.01);
                /* 4 */
                newMarginBotomEnd_date_Txt = (int) (newMarginBotomEnd_date_Txt * 0.01);
                /* 5 */
                newMarginBotomEndDate_Txt = (int) (newMarginBotomEndDate_Txt * 0.07);

            }
            layoutParams_Txt.setMargins(newMarginLeft_Txt, layoutParams_Txt.topMargin, layoutParams_Txt.rightMargin, layoutParams_Txt.bottomMargin);
            layoutParams_Txt1.setMargins(newMarginLeft_Txt, layoutParams_Txt1.topMargin, layoutParams_Txt1.rightMargin, layoutParams_Txt1.bottomMargin);
            layoutParams_TxtStart_Date.setMargins(newMarginLeft_Txt, layoutParams_TxtStart_Date.topMargin, layoutParams_TxtStart_Date.rightMargin, newMarginBotom_Txt);
            layoutParams_Line.setMargins(newMarginLeftAndRight_Line, newMarginBotom_Line, newMarginLeftAndRight_Line, 0);
            layoutParams_TxtStartDate.setMargins(newMarginLeft_Txt, layoutParams_TxtStartDate.topMargin, layoutParams_TxtStartDate.rightMargin, newMarginBotomSatrtDate_Txt);
            layoutParams_TxtEndDate.setMargins(newMarginLeft_Txt, layoutParams_TxtEndDate.topMargin, layoutParams_TxtEndDate.rightMargin, newMarginBotomEndDate_Txt);
            layoutParams_TxtEnd_Date.setMargins(newMarginLeft_Txt, layoutParams_TxtEnd_Date.topMargin, layoutParams_TxtEnd_Date.rightMargin, newMarginBotomEnd_date_Txt);


            holder.UName.setLayoutParams(layoutParams_Txt);
            holder.SName.setLayoutParams(layoutParams_Txt1);
            holder.Start_date.setLayoutParams(layoutParams_TxtStart_Date);
            holder.YurName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_yurname);
            holder.SName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_name);
            holder.Start_date.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_yurname);
            holder.StartDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize_date);
            holder.EndDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize_date);
            holder.End_date.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_yurname);


            holder.Title_txt.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_title);
            holder.TextStatus.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_status);
            holder.UName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize_name);
            //%ImageCardLiner
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.LineCard.getLayoutParams();

            if (screenHeight < 2000 && screenHeight > 1500 && screenWidth >= 1080) {
                int newMarginRight = (int) (screenWidth * 0.1);
                int newMarginBottom = (int) (screenWidth * 0.2);
                int newMarginLeft = (int) (screenWidth * 0.1);
                layoutParams.setMargins(newMarginLeft, layoutParams.topMargin, newMarginRight, newMarginBottom);
                holder.LineCard.setLayoutParams(layoutParams);

            } else {
                int newMarginRight = (int) (screenWidth * 0.08);
                int newMarginBottom = (int) (screenWidth * 0.3);
                int newMarginLeft = (int) (screenWidth * 0.08);
                layoutParams.setMargins(newMarginLeft, layoutParams.topMargin, newMarginRight, newMarginBottom);
                holder.LineCard.setLayoutParams(layoutParams);
            }
            //ImageCircle
            ConstraintLayout constraintLayout = holder.Con1; // Замените your_constraint_layout на ваш ID ConstraintLayout
            View yourView = holder.circleImageView; // Замените your_view на ваш ID элемента
            View yourViewdfl = holder.dflImage;
            ConstraintLayout.LayoutParams layoutParamsCon = (ConstraintLayout.LayoutParams) yourView.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParamsConDflt = (ConstraintLayout.LayoutParams) yourViewdfl.getLayoutParams();
            // circleImage
            if (screenHeight < 2000) {
                float newBias = (float) (layoutParamsCon.horizontalBias * screenHeight * 0.0004);// Новое значение app:layout_constraintHorizontal_bias
                layoutParamsCon.horizontalBias = newBias;
            } else {
                float newBias = (float) (layoutParamsCon.horizontalBias * screenHeight * 0.0006);// Новое значение app:layout_constraintHorizontal_bias
                layoutParamsCon.horizontalBias = newBias;
            }
            //Normal no icon set
            if (screenHeight <= 2000) {
                float newBias = (float) (layoutParamsConDflt.horizontalBias * screenHeight * 0.0002);// Новое значение app:layout_constraintHorizontal_bias
                layoutParamsConDflt.horizontalBias = newBias;
                float newVerBias  = (float) (layoutParamsConDflt.verticalBias * screenHeight * 0.0000001);
                layoutParamsConDflt.verticalBias = newVerBias;
            } else {
                float newBias = (float) (layoutParamsConDflt.horizontalBias * screenHeight * 0.00035);// Новое значение app:layout_constraintHorizontal_bias
                layoutParamsConDflt.horizontalBias = newBias;
                float newVerBias  = (float) (layoutParamsConDflt.verticalBias * screenHeight * 0.00005);
                layoutParamsConDflt.verticalBias = newVerBias;
            }

            yourView.setLayoutParams(layoutParamsCon);
            yourViewdfl.setLayoutParams(layoutParamsConDflt);

            //%circleImageView
            /*ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.circleImageView.getLayoutParams();
            int newMarginRight = (int) (screenWidth * 0.45);
            int newMarginBottom = (int) (screenWidth * 0.2);
            int newMarginLeft  = (int) (layoutParams.leftMargin * 0.5);
            layoutParams.setMargins(newMarginLeft, layoutParams.topMargin, newMarginRight, newMarginBottom);
            holder.circleImageView.setLayoutParams(layoutParams);
            */


        }

        holder.TextStatus.setText(pagerItem.StatusTxt);
        Color color = null;

        holder.TextStatus.setTextColor(pagerItem.color_status);
        holder.Title_txt.setText(pagerItem.AorB_name);
        holder.Start_date.setText(pagerItem.start_date);
        holder.End_date.setText(pagerItem.end_date);
        holder.UName.setText(pagerItem.Uname);
        holder.SName.setText(pagerItem.Sname);
        holder.StartDate.setText(pagerItem.StartDate);
        holder.EndDate.setText(pagerItem.EndDate);
        holder.LineCard.setBackground(pagerItem.StatusImage);

        holder.CountOfGT.setText(pagerItem.CountOfGT);


    }

    @Override
    public int getItemCount() {
        return viePagerItemsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circleImageView;
        ImageView Line;

        ConstraintLayout Con1;
        LinearLayout LineCard;
        TextView YurName;
        ImageView dflImage;
        TextView Title_txt;
        TextView Start_date;
        TextView End_date;
        TextView UName;
        TextView SName;

        TextView CountOfGT;
        ImageView ButM;
        ImageView But2;

        ImageView StatusBut;
        TextView TextStatus;


        TextView StartDate;

        TextView EndDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Line = itemView.findViewById(R.id.imageView3);
            CountOfGT = itemView.findViewById(R.id.CountOfGT);
            Con1 = itemView.findViewById(R.id.Con1);
            LineCard = itemView.findViewById(R.id.LinerCard);
            StatusBut = itemView.findViewById(R.id.StatusBut);
            dflImage = itemView.findViewById(R.id.imageView202);
            YurName = itemView.findViewById(R.id.textView);

            circleImageView = itemView.findViewById(R.id.imageView20);
            TextStatus = itemView.findViewById(R.id.textStatus);
            Title_txt = itemView.findViewById(R.id.abb);
            Start_date = itemView.findViewById(R.id.firstDate);
            End_date = itemView.findViewById(R.id.lastDate);
            UName = itemView.findViewById(R.id.UName);
            SName = itemView.findViewById(R.id.Sname);
            StartDate = itemView.findViewById(R.id.SatrtDate);
            EndDate = itemView.findViewById(R.id.EndDate);

        }
    }

    public void handleButton1Click(int position, int DataCheck, int Count) {
        if (Count != 0 || position == 1) {
            if (DataCheck == 1 || position == 1) {
                switch (position) {
                    case 0:
                        toAFreeze();
                        break;
                    case 1:
                        Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.activity_table);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        ImageView closeButton = dialog.findViewById(R.id.CloseButTab);
                        closeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

// Отображение диалогового окна
                        dialog.show();
                        break;

                }
            } else if (DataCheck == 3) {
                Toast toast = Toast.makeText(context, "Відсутній абонемент", Toast.LENGTH_SHORT);
                toast.show();
            } else if (DataCheck == 2) {
                toAFreezeDrop();
            } else if (Count == 0 && position == 0) {
                Toast toast = Toast.makeText(context, "Функція заморозки вичерпана", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(context, "Функція заморозки вичерпана", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void handleButton2Click(int position, int DataCheck, int Count) {
        if (DataCheck == 1 && Count != 0) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    toGFreeze();
                    break;

            }
        } else if (DataCheck == 3) {
            Toast toast = Toast.makeText(context, "Відсутній абонемент", Toast.LENGTH_SHORT);
            toast.show();
        } else if (DataCheck == 2) {
            toGFreezeDrop();
        } else if (Count == 0) {
            Toast toast = Toast.makeText(context, "Функція заморозки вичерпана", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void toAFreeze() {
        Intent intent = new Intent(context, AFreeze.class);
        context.startActivity(intent);

    }

    public void toTable() {
        Intent intent = new Intent(context, table.class);
        context.startActivity(intent);
    }

    public void toGFreeze() {
        Intent intent = new Intent(context, GFreeze.class);
        context.startActivity(intent);
    }

    public void toAFreezeDrop() {
        Intent intent = new Intent(context, AfreezeDrop.class);
        context.startActivity(intent);
    }

    public void toGFreezeDrop() {
        Intent intent = new Intent(context, GfreezeDrop.class);
        context.startActivity(intent);
    }


}

