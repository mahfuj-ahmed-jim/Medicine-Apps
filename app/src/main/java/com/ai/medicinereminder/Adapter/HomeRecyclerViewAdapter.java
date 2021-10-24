package com.ai.medicinereminder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.Viewholder> {

    private Context context;
    private List <Medicine> list;

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;
    private String morningTime, noonTime, afternoonTime, eveningTime, nightTime;

    // Constructor
    public HomeRecyclerViewAdapter(FragmentActivity context, List <Medicine> list) {
        this.context = context;
        this.list = list;

        // shared preference
        medicineSharedPreference = new MedicineSharedPreference(context);
        this.morningTime = medicineSharedPreference.getData(1);
        this.noonTime = medicineSharedPreference.getData(2);
        this.afternoonTime = medicineSharedPreference.getData(3);
        this.eveningTime = medicineSharedPreference.getData(4);
        this.nightTime = medicineSharedPreference.getData(5);
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
        holder.medicineName.setText(list.get(position).getName());

        if (list.get(position).getDescription() == null) {
            holder.descriptionTextView.setVisibility(View.GONE);
        } else {
            holder.descriptionTextView.setText(list.get(position).getDescription());
        }
        ;

        // time
        holder.morningTime.setText(this.morningTime+" (Morning)");
        holder.noonTime.setText(this.noonTime+" (Noon)");
        holder.afternoonTime.setText(this.afternoonTime+" (Afternoon)");
        holder.eveningTime.setText(this.eveningTime+" (Evening)");
        holder.nightTime.setText(this.nightTime+" (Night)");

        if(list.get(position).getConsumeTime() == 1){
            holder.consumeTimeTextView.setText("Before meal");
        }else if(list.get(position).getConsumeTime() == 2){
            holder.consumeTimeTextView.setText("After meal");
        }else{
            holder.consumeTimeTextView.setText("Anytime");
        }


        // medicine quantity
        if(list.get(position).getMedicineType() == 1){ // tablet

            holder.quantity.setText((int)list.get(position).getMedicineQuantity()>1?
                    (int)list.get(position).getMedicineQuantity()+" Tablets" :
                    (int)list.get(position).getMedicineQuantity()+" Tablet");

        }else if(list.get(position).getMedicineType() == 2){ // syrup

            holder.quantity.setText(list.get(position).getSyrupType()==1?
                    (int)list.get(position).getMedicineQuantity()+" mm" :
                    (int)list.get(position).getMedicineQuantity()==1?
                            (int)list.get(position).getMedicineQuantity()+" Spoon" :
                            (int)list.get(position).getMedicineQuantity()+" Spoons");

        }else if(list.get(position).getMedicineType() == 3){ // dropper

            holder.quantity.setText((int)list.get(position).getMedicineQuantity()>1?
                    (int)list.get(position).getMedicineQuantity()+" Drops" :
                    (int)list.get(position).getMedicineQuantity()+" Drop");

        }else{ // injection

            holder.quantity.setText((int)list.get(position).getMedicineQuantity()>1?
                    (int)list.get(position).getMedicineQuantity()+" Injections" :
                    (int)list.get(position).getMedicineQuantity()+" Injection");

        }

        int count = 0;

        // medicine status imageview
        // morning
        if(!list.get(position).isMorning()){

            holder.morningStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.ellipse_2)
                    .into(holder.morningStatus);

            holder.morningStatus.setVisibility(View.GONE);
            holder.morningTime.setVisibility(View.GONE);
            holder.noonStripe.setVisibility(View.GONE);

        }else{

            holder.morningStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.icon_ellipse_3)
                    .into(holder.morningStatus);
            count++;

        }

        // noon
        if(!list.get(position).isNoon()){

            holder.noonStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.ellipse_2)
                    .into(holder.noonStatus);

            holder.noonTime.setVisibility(View.GONE);
            holder.noonStripe.setVisibility(View.GONE);
            holder.noonStatus.setVisibility(View.GONE);

        }else{

            holder.morningStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.icon_ellipse_3)
                    .into(holder.noonStatus);
            count++;

        }

        // afternoon
        if(!list.get(position).isAfternoon()){

            holder.afternoonStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.ellipse_2)
                    .into(holder.afternoonStatus);

            holder.afternoonStripe.setVisibility(View.GONE);
            holder.afternoonTime.setVisibility(View.GONE);
            holder.afternoonStatus.setVisibility(View.GONE);

        }else{

            holder.afternoonStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.icon_ellipse_3)
                    .into(holder.afternoonStatus);
            count++;

        }

        // evening
        if(!list.get(position).isEvening()){

            holder.eveningStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.ellipse_2)
                    .into(holder.eveningStatus);

            holder.eveningTime.setVisibility(View.GONE);
            holder.eveningStripe.setVisibility(View.GONE);
            holder.eveningStatus.setVisibility(View.GONE);

        }else{

            holder.eveningStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.icon_ellipse_3)
                    .into(holder.eveningStatus);
            count++;

        }

        // night
        if(!list.get(position).isNight()){

            holder.nightStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.ellipse_2)
                    .into(holder.nightStatus);

            holder.nightTime.setVisibility(View.GONE);
            holder.nightStripe.setVisibility(View.GONE);
            holder.nightStatus.setVisibility(View.GONE);

        }else{

            holder.nightStatus.setBackground(null);
            Glide.with(context)
                    .load(list.get(position).isMorning())
                    .placeholder(R.drawable.icon_ellipse_3)
                    .into(holder.nightStatus);
            count++;

        }

        // start stripes
        if(list.get(position).isMorning()==false && list.get(position).isNoon()==false){
            holder.afternoonStripe.setVisibility(View.GONE);
        }
        if(list.get(position).isMorning()==false && list.get(position).isNoon()==false && list.get(position).isAfternoon()==false){
            holder.eveningStripe.setVisibility(View.GONE);
        }
        if(list.get(position).isMorning()==false && list.get(position).isNoon()==false && list.get(position).isAfternoon()==false && list.get(position).isEvening()==false){
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
        return list.size();
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
}
