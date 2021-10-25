package com.ai.medicinereminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ai.medicinereminder.Activity.PageActivity;
import com.ai.medicinereminder.Constant.MedicineConstant;
import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.Database.MedicineHistory;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.Viewholder> {

    // context
    private Context context;

    // medicine list
    private static List <Medicine> list;
    private List<Medicine> filterList;

    // time
    private int currentTime;
    private int morning, noon, afternoon, evening, night;

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;
    private String morningTime, noonTime, afternoonTime, eveningTime, nightTime;

    // room database
    private MainDatabase mainDatabase;

    // Constructor
    public HomeRecyclerViewAdapter(FragmentActivity context, List <Medicine> list) {
        this.context = context;
        this.list = list;
        this.filterList = list;

        // shared preference
        medicineSharedPreference = new MedicineSharedPreference(context);
        this.morningTime = medicineSharedPreference.getData(1);
        this.noonTime = medicineSharedPreference.getData(2);
        this.afternoonTime = medicineSharedPreference.getData(3);
        this.eveningTime = medicineSharedPreference.getData(4);
        this.nightTime = medicineSharedPreference.getData(5);

        // convert time to minutes
        String morningText [] = morningTime.split(" : ");
        String noonText [] = noonTime.split(" : ");
        String afternoonText [] = afternoonTime.split(" : ");
        String eveningText [] = eveningTime.split(" : ");
        String nightText [] = nightTime.split(" : ");

        this.morning = Integer.parseInt(morningText[0])*60 + Integer.parseInt(morningText[1]) +
                (morningText[2].equals("PM")? 720:0);
        this.noon = Integer.parseInt(noonText[0])*60 + Integer.parseInt(noonText[1]) +
                (noonText[2].equals("PM")? 720:0);
        this.afternoon = Integer.parseInt(afternoonText[0])*60 + Integer.parseInt(afternoonText[1]) +
                (afternoonText[2].equals("PM")? 720:0);
        this.evening = Integer.parseInt(eveningText[0])*60 + Integer.parseInt(eveningText[1]) +
                (eveningText[2].equals("PM")? 720:0);
        this.night = Integer.parseInt(nightText[0])*60 + Integer.parseInt(nightText[1]) +
                (nightText[2].equals("PM")? 720:0);

        Date currentTime = Calendar.getInstance().getTime();
        this.currentTime = currentTime.getHours()*60 + currentTime.getMinutes();
        // convert time to minutes

        // room database
        mainDatabase = MainDatabase.getInstance(context);

    }

    @NonNull
    @Override
    public HomeRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_medicine_home_fragment, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {

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

        if (filterList.get(position).getDescription() == null) {
            holder.descriptionTextView.setVisibility(View.GONE);
        } else {
            holder.descriptionTextView.setText(filterList.get(position).getDescription());
        }
        ;

        // time
        holder.morningTime.setText(this.morningTime+" (Morning)");
        holder.noonTime.setText(this.noonTime+" (Noon)");
        holder.afternoonTime.setText(this.afternoonTime+" (Afternoon)");
        holder.eveningTime.setText(this.eveningTime+" (Evening)");
        holder.nightTime.setText(this.nightTime+" (Night)");

        if(filterList.get(position).getConsumeTime() == 1){
            holder.consumeTimeTextView.setText("Before meal");
        }else if(filterList.get(position).getConsumeTime() == 2){
            holder.consumeTimeTextView.setText("After meal");
        }else{
            holder.consumeTimeTextView.setText("Anytime");
        }


        // medicine quantity
        if(filterList.get(position).getMedicineType() == 1){ // tablet

            holder.quantity.setText((int)filterList.get(position).getMedicineQuantity()>1?
                    (int)filterList.get(position).getMedicineQuantity()+" Tablets" :
                    (int)filterList.get(position).getMedicineQuantity()+" Tablet");

        }else if(filterList.get(position).getMedicineType() == 2){ // syrup

            holder.quantity.setText(filterList.get(position).getSyrupType()==1?
                    (int)filterList.get(position).getMedicineQuantity()+" mm" :
                    (int)filterList.get(position).getMedicineQuantity()==1?
                            (int)filterList.get(position).getMedicineQuantity()+" Spoon" :
                            (int)filterList.get(position).getMedicineQuantity()+" Spoons");

        }else if(filterList.get(position).getMedicineType() == 3){ // dropper

            holder.quantity.setText((int)filterList.get(position).getMedicineQuantity()>1?
                    (int)filterList.get(position).getMedicineQuantity()+" Drops" :
                    (int)filterList.get(position).getMedicineQuantity()+" Drop");

        }else{ // injection

            holder.quantity.setText((int)filterList.get(position).getMedicineQuantity()>1?
                    (int)filterList.get(position).getMedicineQuantity()+" Injections" :
                    (int)filterList.get(position).getMedicineQuantity()+" Injection");

        }

        int count = 0;

        // medicine status imageview
        // morning
        if(!filterList.get(position).isMorning()){

            holder.morningStatus.setVisibility(View.GONE);
            holder.morningTime.setVisibility(View.GONE);
            holder.noonStripe.setVisibility(View.GONE);

        }else{

            if(currentTime >= morning){

                if(getMedicineHistory(list.get(position).getMedicineID()).isMorning()){

                    holder.morningStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.icon_ellipse_3)
                            .into(holder.morningStatus);

                }else{

                    holder.morningStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.ellipse_red)
                            .into(holder.morningStatus);

                    holder.morningTime.setTextColor(ContextCompat.getColor(context, R.color.red));

                }

            }else{

                holder.morningStatus.setBackground(null);
                Glide.with(context)
                        .load(filterList.get(position).isMorning())
                        .placeholder(R.drawable.ellipse_2)
                        .into(holder.morningStatus);

                holder.morningTime.setTypeface(holder.morningTime.getTypeface(), Typeface.BOLD);

            }

            count++;

        }

        // noon
        if(!filterList.get(position).isNoon()){

            holder.noonTime.setVisibility(View.GONE);
            holder.noonStripe.setVisibility(View.GONE);
            holder.noonStatus.setVisibility(View.GONE);

        }else{

            if(currentTime >= noon){

                if(getMedicineHistory(list.get(position).getMedicineID()).isNoon()){

                    holder.noonStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.icon_ellipse_3)
                            .into(holder.noonStatus);

                }else{

                    holder.noonStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.ellipse_red)
                            .into(holder.noonStatus);

                    holder.noonTime.setTextColor(ContextCompat.getColor(context, R.color.red));

                }

            }else{

                holder.noonStatus.setBackground(null);
                Glide.with(context)
                        .load(filterList.get(position).isMorning())
                        .placeholder(R.drawable.ellipse_2)
                        .into(holder.noonStatus);

                holder.noonTime.setTypeface(holder.noonTime.getTypeface(), Typeface.BOLD);

            }

            count++;

        }

        // afternoon
        if(!filterList.get(position).isAfternoon()){

            holder.afternoonStripe.setVisibility(View.GONE);
            holder.afternoonTime.setVisibility(View.GONE);
            holder.afternoonStatus.setVisibility(View.GONE);

        }else{

            if(currentTime >= afternoon){

                if(getMedicineHistory(list.get(position).getMedicineID()).isAfternoon()){

                    holder.afternoonStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.icon_ellipse_3)
                            .into(holder.afternoonStatus);

                }else{

                    holder.afternoonStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.ellipse_red)
                            .into(holder.afternoonStatus);

                    holder.afternoonTime.setTextColor(ContextCompat.getColor(context, R.color.red));

                }

            }else{

                holder.afternoonStatus.setBackground(null);
                Glide.with(context)
                        .load(filterList.get(position).isMorning())
                        .placeholder(R.drawable.ellipse_2)
                        .into(holder.afternoonStatus);

                holder.afternoonTime.setTypeface(holder.afternoonTime.getTypeface(), Typeface.BOLD);

            }

            count++;

        }

        // evening
        if(!filterList.get(position).isEvening()){

            holder.eveningTime.setVisibility(View.GONE);
            holder.eveningStripe.setVisibility(View.GONE);
            holder.eveningStatus.setVisibility(View.GONE);

        }else{

            if(currentTime >= evening){

                if(getMedicineHistory(list.get(position).getMedicineID()).isEvening()){

                    holder.eveningStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.icon_ellipse_3)
                            .into(holder.eveningStatus);

                }else{

                    holder.eveningStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.ellipse_red)
                            .into(holder.eveningStatus);

                    holder.eveningTime.setTextColor(ContextCompat.getColor(context, R.color.red));

                }

            }else{

                holder.eveningStatus.setBackground(null);
                Glide.with(context)
                        .load(filterList.get(position).isMorning())
                        .placeholder(R.drawable.ellipse_2)
                        .into(holder.eveningStatus);

                holder.eveningTime.setTypeface(holder.eveningTime.getTypeface(), Typeface.BOLD);

            }

            count++;

        }

        // night
        if(!filterList.get(position).isNight()){

            holder.nightTime.setVisibility(View.GONE);
            holder.nightStripe.setVisibility(View.GONE);
            holder.nightStatus.setVisibility(View.GONE);

        }else{

            if(currentTime >= night){

                if(getMedicineHistory(list.get(position).getMedicineID()).isNight()){

                    holder.nightStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.icon_ellipse_3)
                            .into(holder.nightStatus);

                }else{

                    holder.nightStatus.setBackground(null);
                    Glide.with(context)
                            .load(filterList.get(position).isMorning())
                            .placeholder(R.drawable.ellipse_red)
                            .into(holder.nightStatus);

                    holder.nightTime.setTextColor(ContextCompat.getColor(context, R.color.red));

                }

            }else{

                holder.nightStatus.setBackground(null);
                Glide.with(context)
                        .load(filterList.get(position).isMorning())
                        .placeholder(R.drawable.ellipse_2)
                        .into(holder.nightStatus);

                holder.nightTime.setTypeface(holder.nightTime.getTypeface(), Typeface.BOLD);

            }

            count++;

        }

        // start stripes
        if(filterList.get(position).isMorning()==false && filterList.get(position).isNoon()==false){
            holder.afternoonStripe.setVisibility(View.GONE);
        }
        if(filterList.get(position).isMorning()==false && filterList.get(position).isNoon()==false && filterList.get(position).isAfternoon()==false){
            holder.eveningStripe.setVisibility(View.GONE);
        }
        if(filterList.get(position).isMorning()==false && filterList.get(position).isNoon()==false && filterList.get(position).isAfternoon()==false && filterList.get(position).isEvening()==false){
            holder.nightStripe.setVisibility(View.GONE);
        }
        // end stripes

        if (count == 1) {
            holder.dailyTime.setText("Daily " + count + " time");
        } else {
            holder.dailyTime.setText("Daily " + count + " times");
        }

        if(count==1){
            holder.bottomLayout.setVisibility(View.VISIBLE);
        }

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

    public MedicineHistory getMedicineHistory(int id){

        MedicineHistory medicineHistory = new MedicineHistory();

        for(MedicineHistory med : mainDatabase.medicineHistoryDao().getMedicineHistoryList()){

            if(med.getMedicineID() == id){

                medicineHistory = med;
                break;

            }

        }

        return medicineHistory;

    }


    public class Viewholder extends RecyclerView.ViewHolder {

        // background
        private ConstraintLayout backgroundLayout;

        // textView
        private TextView medicineName;
        private TextView descriptionTextView;
        private TextView dailyTime;
        private TextView morningTime, noonTime, afternoonTime, eveningTime, nightTime;
        private TextView consumeTimeTextView;
        private TextView quantity;

        // medicine status imageView
        private ImageView morningStatus, noonStatus, afternoonStatus, eveningStatus, nightStatus;
        private LinearLayout noonStripe, afternoonStripe, eveningStripe, nightStripe, bottomLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            // background
            backgroundLayout = itemView.findViewById(R.id.constraintLayout_background);

            // textView
            medicineName = itemView.findViewById(R.id.textViewId_medicineName);
            descriptionTextView = itemView.findViewById(R.id.textView_description);
            dailyTime = itemView.findViewById(R.id.textView_dailyTime);

            morningTime = itemView.findViewById(R.id.textView_morningTime);
            noonTime = itemView.findViewById(R.id.textView_noonTime);
            afternoonTime = itemView.findViewById(R.id.textView_afternoonTime);
            eveningTime = itemView.findViewById(R.id.textView_eveningTime);
            nightTime = itemView.findViewById(R.id.textView_nightTime);

            consumeTimeTextView = itemView.findViewById(R.id.textViewId_consumeTime);
            quantity = itemView.findViewById(R.id.textView_quantity);

            // medicine status imageview
            morningStatus = itemView.findViewById(R.id.imageView_morningImage);
            noonStatus = itemView.findViewById(R.id.imageView_noonImage);
            afternoonStatus = itemView.findViewById(R.id.imageView_afternoonImage);
            eveningStatus = itemView.findViewById(R.id.imageView_eveningImage);
            nightStatus = itemView.findViewById(R.id.imageView_nightImage);

            noonStripe = itemView.findViewById(R.id.linearLayout_noonStripe);
            afternoonStripe = itemView.findViewById(R.id.linearLayout_afternoonStripe);
            eveningStripe = itemView.findViewById(R.id.linearLayout_eveningStripe);
            nightStripe = itemView.findViewById(R.id.linearLayout_nightStripe);
            bottomLayout = itemView.findViewById(R.id.linearLayout_bottom);

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
