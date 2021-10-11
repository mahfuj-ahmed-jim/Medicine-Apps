package com.ai.medicinereminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;

import java.util.ArrayList;

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.Viewholder> {

    private Context context;
    private ArrayList<Medicine> list;

    // Constructor
    public HomeCardAdapter(FragmentActivity context, ArrayList<Medicine> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeCardAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_medicine_home_fragment, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HomeCardAdapter.Viewholder holder, int position) {

        Medicine model = list.get(position);

        holder.home_medicineName.setText(""+model.getName());
        holder.home_medicineQuantity.setText(""+model.getMedicineQuantity());
        holder.home_ConsumeTime.setText(""+model.getConsumeTime());
        holder.home_Morning.setText(""+model.isMorning());
        holder.home_Noon.setText(""+model.isNoon());
        holder.home_Afternoon.setText(""+model.isAfternoon());
        holder.home_Evening.setText(""+model.isEvening());
        holder.home_Night.setText(""+model.isNight());

        if(position%2==0 ){
            holder.home_card_bg.setBackgroundColor(R.color.card_purple);
        }
        if(position%3==0 || position == 1){
            holder.home_card_bg.setBackgroundColor(R.color.card_red);
        }
        if(position%5==0 || position == 0){
            holder.home_card_bg.setBackgroundColor(R.color.card_green);
        }
        if(position%7==0){
            holder.home_card_bg.setBackgroundColor(R.color.card_yellow);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        //TextView
        private TextView home_medicineName,home_medicineQuantity,home_ConsumeTime,home_Morning,home_Noon,home_Afternoon,home_Evening,home_Night;

        //ImageView
        private ImageView home_medicineTypeImage;

        //Layout
        private ConstraintLayout home_card_bg;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            home_medicineName = itemView.findViewById(R.id.home_medicineName);
            home_medicineQuantity = itemView.findViewById(R.id.home_medicineQuantity);
            home_ConsumeTime = itemView.findViewById(R.id.home_ConsumeTime);
            home_Morning = itemView.findViewById(R.id.home_Morning);
            home_Noon = itemView.findViewById(R.id.home_Noon);
            home_Afternoon = itemView.findViewById(R.id.home_Afternoon);
            home_Evening = itemView.findViewById(R.id.home_Evening);
            home_Night = itemView.findViewById(R.id.home_Night);
            home_medicineTypeImage = itemView.findViewById(R.id.home_medicineTypeImage);

            home_card_bg = itemView.findViewById(R.id.home_card_frame_bg);


        }
    }
}
