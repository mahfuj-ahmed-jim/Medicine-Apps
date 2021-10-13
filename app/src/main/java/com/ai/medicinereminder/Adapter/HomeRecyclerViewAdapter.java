package com.ai.medicinereminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.Viewholder> {

    private Context context;
    private ArrayList<Medicine> list;

    // Constructor
    public HomeRecyclerViewAdapter(FragmentActivity context, ArrayList<Medicine> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_medicine_home_fragment, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewAdapter.Viewholder holder, int position) {
        int count=0;

        Medicine model = list.get(position);

        holder.medicineName.setText(""+model.getName());


        if(model.getConsumeTime()==1){
            holder.consumeTime.setText("Before Meal");
        }else{
            holder.consumeTime.setText("After Meal");
        }
        if(model.isMorning()== true){
            count++;
        }
        if(model.isNoon()== true){
            count++;
        }
        if(model.isAfternoon()== true){
            count++;
        }
        if(model.isEvening()== true){
            count++;
        }
        if(model.isNight()== true){
            count++;
        }

        holder.medicineDaily.setText("Daily "+count+" times");
        holder.morning.setText(""+model.isMorning());
        holder.noon.setText(""+model.isNoon());
        holder.afternoon.setText(""+model.isAfternoon());
        holder.evening.setText(""+model.isEvening());
        holder.night.setText(""+model.isNight());


//        if(position%2==0 ){
//            holder.recyclerView_bg.setBackgroundColor(R.color.card_purple);
//        }
//        if(position%3==0 || position == 1){
//            holder.recyclerView_bg.setBackgroundColor(R.color.card_red);
//        }
//        if(position%5==0 || position == 0){
//            holder.recyclerView_bg.setBackgroundColor(R.color.card_green);
//        }
//        if(position%7==0){
//            holder.recyclerView_bg.setBackgroundColor(R.color.card_yellow);
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        //TextView
        private TextView medicineName,medicineDaily,consumeTime,morning,noon,afternoon,evening,night;

        //ImageView
        private ImageView medicineTypeImage;

        //Layout
        private ConstraintLayout recyclerView_bg;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.home_medicineName);
            medicineDaily = itemView.findViewById(R.id.home_medicineDaily);
            consumeTime = itemView.findViewById(R.id.home_ConsumeTime);
            morning = itemView.findViewById(R.id.home_Morning);
            noon = itemView.findViewById(R.id.home_Noon);
            afternoon = itemView.findViewById(R.id.home_Afternoon);
            evening = itemView.findViewById(R.id.home_Evening);
            night = itemView.findViewById(R.id.home_Night);
            medicineTypeImage = itemView.findViewById(R.id.home_medicineTypeImage);

            recyclerView_bg = itemView.findViewById(R.id.home_recyclerview_bg);


        }
    }
}
