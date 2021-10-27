package com.ai.medicinereminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.Database.MedicineHistory;
import com.ai.medicinereminder.R;

import java.util.ArrayList;
import java.util.List;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmRecyclerViewAdapter.Viewholder> {

    // room database
    private MainDatabase mainDatabase;

    private Context context;
    private int session;
    private List<Medicine> filterList;
    private List <MedicineHistory> medicineHistoryList = new ArrayList<>();

    // Constructor
    public AlarmRecyclerViewAdapter(FragmentActivity context, List <Medicine> list, int session) {

        this.context = context;
        this.filterList = list;
        this.session = session;

        // room database
        mainDatabase = MainDatabase.getInstance(context);
        setUpMedicineHistoryList();

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

        // background color
        if(position%4 == 0){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_green));
        }else if(position%4 == 1){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_purple));
        }else if(position%4 == 2){
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_red));
        }else{
            holder.backgroundLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.card_yellow));
        }

        // textView
        holder.medicineName.setText(filterList.get(position).getName());
        holder.descriptionTextView.setText(filterList.get(position).getDescription());

        // medicine quantity
        if(filterList.get(position).getMedicineType() == 1){ // tablet

            holder.quantityTextView.setText((int)filterList.get(position).getMedicineQuantity()>1?
                    (int)filterList.get(position).getMedicineQuantity()+" Tablets" :
                    (int)filterList.get(position).getMedicineQuantity()+" Tablet");

        }else if(filterList.get(position).getMedicineType() == 2){ // syrup

            holder.quantityTextView.setText(filterList.get(position).getSyrupType()==1?
                    (int)filterList.get(position).getMedicineQuantity()+" mm" :
                    (int)filterList.get(position).getMedicineQuantity()==1?
                            (int)filterList.get(position).getMedicineQuantity()+" Spoon" :
                            (int)filterList.get(position).getMedicineQuantity()+" Spoons");

        }else if(filterList.get(position).getMedicineType() == 3){ // dropper

            holder.quantityTextView.setText((int)filterList.get(position).getMedicineQuantity()>1?
                    (int)filterList.get(position).getMedicineQuantity()+" Drops" :
                    (int)filterList.get(position).getMedicineQuantity()+" Drop");

        }else{ // injection

            holder.quantityTextView.setText((int)filterList.get(position).getMedicineQuantity()>1?
                    (int)filterList.get(position).getMedicineQuantity()+" Injections" :
                    (int)filterList.get(position).getMedicineQuantity()+" Injection");

        }

        // check boxes
        switch (session){

            case 1 :
                holder.medicineCheckbox.setChecked(medicineHistoryList.get(position).isMorning());
                break;

            case 2 :
                holder.medicineCheckbox.setChecked(medicineHistoryList.get(position).isNoon());
                break;

            case 3 :
                holder.medicineCheckbox.setChecked(medicineHistoryList.get(position).isAfternoon());
                break;

            case 4 :
                holder.medicineCheckbox.setChecked(medicineHistoryList.get(position).isEvening());
                break;

            case 5 :
                holder.medicineCheckbox.setChecked(medicineHistoryList.get(position).isNight());
                break;

        }

        holder.medicineCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){ // set unChecked

                    switch (session){

                        case 1 :
                            medicineHistoryList.get(position).setMorning(true);
                            break;

                        case 2 :
                            medicineHistoryList.get(position).setNoon(true);
                            break;

                        case 3 :
                            medicineHistoryList.get(position).setAfternoon(true);
                            break;

                        case 4 :
                            medicineHistoryList.get(position).setEvening(true);
                            break;

                        case 5 :
                            medicineHistoryList.get(position).setNight(true);
                            break;

                    }

                }else{ // set checked

                    switch (session){

                        case 1 :
                            medicineHistoryList.get(position).setMorning(false);
                            break;

                        case 2 :
                            medicineHistoryList.get(position).setNoon(false);
                            break;

                        case 3 :
                            medicineHistoryList.get(position).setAfternoon(false);
                            break;

                        case 4 :
                            medicineHistoryList.get(position).setEvening(false);
                            break;

                        case 5 :
                            medicineHistoryList.get(position).setNight(false);
                            break;

                    }

                }

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

    private void setUpMedicineHistoryList() {

        for(Medicine medicine : filterList){

            for(MedicineHistory medicineHistory : mainDatabase.medicineHistoryDao().getMedicineHistoryList()){

                if(medicineHistory.getMedicineID() == medicine.getMedicineID()){

                    medicineHistoryList.add(medicineHistory);
                    break;

                }

            }

        }

    }

    public void setAllChecked(){

        switch (session){

            case 1 :

                int index1 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setMorning(true);
                    medicineHistoryList.set(index1, medicineHistory);

                }

                break;

            case 2 :

                int index2 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setNoon(true);
                    medicineHistoryList.set(index2, medicineHistory);

                }

                break;

            case 3 :

                int index3 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setAfternoon(true);
                    medicineHistoryList.set(index3, medicineHistory);

                }

                break;

            case 4 :

                int index4 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setEvening(true);
                    medicineHistoryList.set(index4, medicineHistory);

                }

                break;

            case 5 :

                int index5 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setNight(true);
                    medicineHistoryList.set(index5, medicineHistory);

                }

                break;

        }

    }

    public void setAllUnChecked(){

        switch (session){

            case 1 :

                int index1 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setMorning(false);
                    medicineHistoryList.set(index1, medicineHistory);

                }

                break;

            case 2 :

                int index2 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setNoon(false);
                    medicineHistoryList.set(index2, medicineHistory);

                }

                break;

            case 3 :

                int index3 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setAfternoon(false);
                    medicineHistoryList.set(index3, medicineHistory);

                }

                break;

            case 4 :

                int index4 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setEvening(false);
                    medicineHistoryList.set(index4, medicineHistory);

                }

                break;

            case 5 :

                int index5 = 0;

                for(MedicineHistory medicineHistory : medicineHistoryList){

                    medicineHistory.setNight(false);
                    medicineHistoryList.set(index5, medicineHistory);

                }

                break;

        }

    }

    public void updateMedicineHistory(){

        for(MedicineHistory medicineHistory : medicineHistoryList){

            mainDatabase.medicineHistoryDao().updateMedicineHistory(medicineHistory);

        }

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
