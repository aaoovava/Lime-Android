package com.example.limeapp.core.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.limeapp.core.adapters.TimeTableAdapter;
import com.example.limeapp.core.adapters.TimeTableDatesAdapter;
import com.example.limeapp.core.interfaces.OnDateClickListener;
import com.example.limeapp.R;
import com.example.limeapp.ob_class.DateItem;
import com.example.limeapp.ob_class.TimeTableItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTableDialog extends DialogFragment implements OnDateClickListener {
    private ImageView closeBut;
    private RecyclerView Table;
    private RecyclerView Dates;
    private TimeTableAdapter timeTableAdapter;
    private TimeTableDatesAdapter timeTableDatesAdapter;
    private ArrayList<DateItem>timeTableDateItems;
    private FirebaseDatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timetable, container, false);
        closeBut = view.findViewById(R.id.quit_but);

        db = FirebaseDatabase.getInstance();
        DatabaseReference MondayRef = db.getReference("TimeTable/Monday");
        DatabaseReference TuesdayRef = db.getReference("TimeTable/Tuesday");
        DatabaseReference WednesdayRef = db.getReference("TimeTable/Wednesday");
        DatabaseReference ThursdayRef = db.getReference("TimeTable/Thursday");
        DatabaseReference FridayRef = db.getReference("TimeTable/Friday");
        DatabaseReference SaturdayRef = db.getReference("TimeTable/Saturday");
        DatabaseReference SundayRef = db.getReference("TimeTable/Sunday");

        HashMap<String,TimeTableItem>list = new HashMap<>();
        list.put("Функціонал", new TimeTableItem("Функціонал", "Сергій Нех", "09:00", "09:00"));
        list.put("TRX", new TimeTableItem("TRX", "Вікторія Грек", "10:00", "10:00"));
        list.put("Бодіфлекс Оксісайз Бодібалет", new TimeTableItem("Бодіфлекс Оксісайз Бодібалет", "Олена Добрякова", "13:00", "13:00"));
        list.put("Функціонал 2", new TimeTableItem("Функціонал", "Вікторія Грек", "14:00", "14:00"));
        list.put("Дитячі заняття 8-12 років", new TimeTableItem("Дитячі заняття 8-12 років", "Ніна Вороніна", "15:00", "15:00"));
        list.put("Шейпінг", new TimeTableItem("Шейпінг", "Ніна Вороніна", "16:00", "16:00"));

        SaturdayRef.setValue(list);


        Table = view.findViewById(R.id.Table);
        Dates = view.findViewById(R.id.Dates);
        Dates.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeTableDateItems = DateItem.generateWeek();
        }
        timeTableDatesAdapter = new TimeTableDatesAdapter(getContext(),timeTableDateItems);
        timeTableDatesAdapter.setListener(TimeTableDialog.this);
        Dates.setAdapter(timeTableDatesAdapter);


        closeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }
    private void setTimeTable(DatabaseReference databaseReference, Context context){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<TimeTableItem>timeTableItems = new ArrayList<>();
                timeTableAdapter = new TimeTableAdapter(context, timeTableItems);
                Table.setLayoutManager(new LinearLayoutManager(getActivity()));
                Table.setAdapter(timeTableAdapter);
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    timeTableItems.add(dataSnapshot.getValue(TimeTableItem.class));
                }
                timeTableItems = TimeTableItem.sortTimeTableItemList(timeTableItems);
                timeTableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public  void onItemClick(DateItem dateItem) {
        db = FirebaseDatabase.getInstance();
        DatabaseReference MondayRef = db.getReference("TimeTable/Monday");
        DatabaseReference TuesdayRef = db.getReference("TimeTable/Tuesday");
        DatabaseReference WednesdayRef = db.getReference("TimeTable/Wednesday");
        DatabaseReference ThursdayRef = db.getReference("TimeTable/Thursday");
        DatabaseReference FridayRef = db.getReference("TimeTable/Friday");
        DatabaseReference SaturdayRef = db.getReference("TimeTable/Saturday");
        DatabaseReference SundayRef = db.getReference("TimeTable/Sunday");

        switch (dateItem.getDay()){
            case "Понеділок":
                setTimeTable(MondayRef, getContext());
                break;
            case "Вівторок":
                setTimeTable(TuesdayRef, getContext());
                break;
            case "Середа":
                setTimeTable(WednesdayRef, getContext());
                break;
            case "Четвер":
                setTimeTable(ThursdayRef, getContext());
                break;
            case "Пʼятниця":
                setTimeTable(FridayRef, getContext());
                break;
            case "Субота":
                setTimeTable(SaturdayRef, getContext());
                break;
            case "Неділя":
                setTimeTable(SundayRef, getContext());
                break;
            default:
                break;
        }
    }

}
