package com.ai.medicinereminder.PageActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ai.medicinereminder.R;

import java.util.ArrayList;

public class home_Card_Adapter extends RecyclerView.Adapter<home_Card_Adapter.Viewholder> {

    private Context context;
    private ArrayList<Model_home_card> list;

    // Constructor
    public home_Card_Adapter(FragmentActivity context, ArrayList<Model_home_card> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public home_Card_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_medicine_home_fragment, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull home_Card_Adapter.Viewholder holder, int position) {

        Model_home_card model = list.get(position);

        holder.home_medicineName.setText(""+model.getMedicineName());
        holder.home_medicineCount.setText(""+model.getMedicineCount());
        holder.home_meal.setText(""+model.getMeal());
        holder.home_time01.setText(""+model.medicineTime01);
        holder.home_time02.setText(""+model.medicineTime02);
        holder.home_time03.setText(""+model.medicineTime03);
        holder.home_time04.setText(""+model.medicineTime04);
        holder.home_time05.setText(""+model.medicineTime05);

        if(position%2==0 ){
            holder.home_card_frame_bg.setBackgroundColor(R.color.card_purple);
        }
        if(position%3==0 || position == 1){
            holder.home_card_frame_bg.setBackgroundColor(R.color.card_red);
        }
        if(position%5==0 || position == 0){
            holder.home_card_frame_bg.setBackgroundColor(R.color.card_green);
        }
        if(position%7==0){
            holder.home_card_frame_bg.setBackgroundColor(R.color.card_yellow);
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView home_medicineName,home_medicineCount,home_meal,home_time01,home_time02,home_time03,home_time04,home_time05;
        private ImageView home_medicineType;
        private FrameLayout home_card_frame_bg;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            home_medicineName = itemView.findViewById(R.id.home_medicineName);
            home_medicineCount = itemView.findViewById(R.id.home_medicineCount);
            home_meal = itemView.findViewById(R.id.home_meal);
            home_time01 = itemView.findViewById(R.id.home_time01);
            home_time02 = itemView.findViewById(R.id.home_time02);
            home_time03 = itemView.findViewById(R.id.home_time03);
            home_time04 = itemView.findViewById(R.id.home_time04);
            home_time05 = itemView.findViewById(R.id.home_time05);
            home_medicineType = itemView.findViewById(R.id.home_medicineType);

            home_card_frame_bg = itemView.findViewById(R.id.home_card_frame_bg);


        }
    }
}
