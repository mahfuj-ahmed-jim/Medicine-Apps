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
import androidx.core.content.ContextCompat;
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

        // background color
        if(position%4 == 0){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_purple));
        }else if(position%4 == 1){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_green));
        }else if(position%4 == 2){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_yellow));
        }else{
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_red));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        // background
        private ConstraintLayout backgroundLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            // background
            backgroundLayout = itemView.findViewById(R.id.constraintLayout_background);

        }
    }
}
