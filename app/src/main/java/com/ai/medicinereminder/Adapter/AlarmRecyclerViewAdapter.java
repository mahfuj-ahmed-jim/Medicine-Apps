package com.ai.medicinereminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;

import java.util.List;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.Viewholder> {

    private Context context;
    private List<Medicine> filterList;

    // Constructor
    public AlarmRecyclerViewAdapter(FragmentActivity context, List <Medicine> list) {
        this.context = context;
        this.filterList = list;
    }

    @NonNull
    @Override
    public AlarmRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_alarm, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AlarmRecyclerViewAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {

        /*// background color
        if(position%4 == 0){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_red));
        }else if(position%4 == 1){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_yellow));
        }else if(position%4 == 2){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_green));
        }else{
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_purple));
        }

        // textView
        holder.medicineName.setText(filterList.get(position).getName());*/

    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public int getItemCount() {
        return 7;
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        // background
        private ConstraintLayout backgroundLayout;

        // textView
        private TextView medicineName;
        private TextView quantityTextView;
        private TextView descriptionTextView;

        // check box
        private CheckBox medicineCheckbox;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            // background
            backgroundLayout = itemView.findViewById(R.id.constraintLayout_background);

            // textView
            medicineName = itemView.findViewById(R.id.textViewId_medicineName);
            quantityTextView = itemView.findViewById(R.id.textView_quantity);
            descriptionTextView = itemView.findViewById(R.id.textView_description);

            // checkbox
            medicineCheckbox = itemView.findViewById(R.id.checkBoxId_medicine);

        }
    }

}
