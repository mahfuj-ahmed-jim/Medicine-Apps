package com.ai.medicinereminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ai.medicinereminder.Activity.MainActivity;
import com.ai.medicinereminder.Activity.PageActivity;
import com.ai.medicinereminder.Constant.MedicineConstant;
import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.MainActivity.HomeFragment;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MedicineRecyclerViewAdapter extends RecyclerView.Adapter<MedicineRecyclerViewAdapter.Viewholder> {

    private Context context;
    private List <Medicine> list;
    private List<Medicine> filterList;

    // Constructor
    public MedicineRecyclerViewAdapter(FragmentActivity context, List <Medicine> list) {
        this.context = context;
        this.list = list;
        this.filterList = list;
    }

    @NonNull
    @Override
    public MedicineRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_medicine, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MedicineRecyclerViewAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {

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

        // textView
        holder.medicineName.setText(filterList.get(position).getName());

        int count = 0;

        // medicine status imageview
        // morning
        if(filterList.get(position).isMorning()){
            count++;
        }
        // noon
        if(filterList.get(position).isNoon()){
            count++;
        }
        // afternoon
        if(filterList.get(position).isAfternoon()){
            count++;
        }
        // evening
        if(filterList.get(position).isEvening()){
            count++;
        }
        // night
        if(filterList.get(position).isNight()){
            count++;
        }

        if (count == 1) {
            holder.dailyTime.setText("Daily " + count + " time");
        } else {
            holder.dailyTime.setText("Daily " + count + " times");
        }


        // on click listener
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MedicineConstant medicineConstant = new MedicineConstant();
                medicineConstant.setMedicineId(filterList.get(position).getMedicineID()+"");

                Intent intent = new Intent(context, PageActivity.class);
                intent.putExtra(context.getString(R.string.activity),
                        context.getString(R.string.addMedicine));
                context.startActivity(intent);

            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Medicine medicine = filterList.get(position);

                // alert dialog
                AlertDialog.Builder builder;

                // alert dialog
                builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to delete "+medicine.getName()+"?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                try{

                                    // room database
                                    MainDatabase mainDatabase = MainDatabase.getInstance(context);
                                    mainDatabase.medicineDao().deleteMedicine(medicine);

                                    filterList.remove(medicine);
                                    list.remove(medicine);
                                    notifyDataSetChanged();

                                }catch (Exception e){

                                    Log.d("Verify", e.getMessage());

                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //  Action for 'NO' Button

                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Delete medicine");
                alert.show();

            }
        });

    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        // background
        private ConstraintLayout backgroundLayout;

        // textView
        private TextView medicineName;
        private TextView descriptionTextView;
        private TextView dailyTime;

        // button
        private Button editButton, deleteButton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            // background
            backgroundLayout = itemView.findViewById(R.id.constraintLayout_background);

            // textView
            medicineName = itemView.findViewById(R.id.textViewId_medicineName);
            descriptionTextView = itemView.findViewById(R.id.textView_description);
            dailyTime = itemView.findViewById(R.id.textView_dailyTime);

            // button
            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);

        }
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if(key.isEmpty()){
                    filterList = list;
                }else{
                    List<Medicine> listFilter = new ArrayList<>();
                    for(Medicine medicine:list){
                        if(medicine.getName().toLowerCase().contains(key.toLowerCase())){
                            listFilter.add(medicine);
                        }
                    }
                    filterList = listFilter;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (List<Medicine>)results.values;
                notifyDataSetChanged();
            }
        };
    }

}
