package com.ai.medicinereminder.PageActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ai.medicinereminder.Alarm.Alarm;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.AlarmSharedPreference;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;

import java.util.Calendar;

public class MedicineTimeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button backButton;

    // alarm
    private Alarm alarm;

    // share preference
    private MedicineSharedPreference medicineSharedPreference;
    private AlarmSharedPreference alarmSharedPreference;

    // textViews
    private TextView morningTime, noonTime, afternoonTime,eveningTime, nightTime;

    // switches
    private Switch morningSwitch, noonSwitch, afternoonSwitch, eveningSwitch, nightSwitch;

    // layouts
    private ConstraintLayout morningLayout, noonLayout, afternoonLayout, eveningLayout, nightLayout;

    public MedicineTimeFragment() {
        // Required empty public constructor
    }

   // TODO: Rename and change types and number of parameters
    public static MedicineTimeFragment newInstance(String param1, String param2) {
        MedicineTimeFragment fragment = new MedicineTimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine_time, container, false);

        // alarm
        alarm = new Alarm(getActivity().getApplicationContext());

        // textView
        morningTime = view.findViewById(R.id.textViewId_morning);
        noonTime = view.findViewById(R.id.textViewId_noon);
        afternoonTime = view.findViewById(R.id.textViewId_afternoon);
        eveningTime = view.findViewById(R.id.textViewId_evening);
        nightTime = view.findViewById(R.id.textViewId_night);
        backButton = view.findViewById(R.id.back_button_id);

        // switches
        morningSwitch = view.findViewById(R.id.switchId_morning);
        noonSwitch = view.findViewById(R.id.switchId_noon);
        afternoonSwitch = view.findViewById(R.id.switchId_afternoon);
        eveningSwitch = view.findViewById(R.id.switchId_evening);
        nightSwitch = view.findViewById(R.id.switchId_night);

        // layouts
        morningLayout = view.findViewById(R.id.constraintId_morning);
        noonLayout = view.findViewById(R.id.constraintId_noon);
        afternoonLayout = view.findViewById(R.id.constraintId_afternoon);
        eveningLayout = view.findViewById(R.id.constraintId_evening);
        nightLayout = view.findViewById(R.id.constraintId_night);

        // set up timer textViews & switch
        medicineSharedPreference = new MedicineSharedPreference(getActivity().getApplicationContext());
        alarmSharedPreference = new AlarmSharedPreference(getActivity().getApplicationContext());
        initializeTimerTextViews();
        initializeTimerTextSwitches();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        morningLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimer(1);
            }
        });

        noonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimer(2);

            }
        });

        afternoonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimer(3);

            }
        });

        eveningLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimer(4);

            }
        });

        nightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTimer(5);

            }
        });

        morningSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    alarmSharedPreference.setData(1, true);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm on for morning", Toast.LENGTH_LONG).show();

                }else{

                    alarmSharedPreference.setData(1, false);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm off for morning", Toast.LENGTH_LONG).show();

                }

            }
        });

        noonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    alarmSharedPreference.setData(2, true);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm on for noon", Toast.LENGTH_LONG).show();

                }else{

                    alarmSharedPreference.setData(2, false);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm off for noon", Toast.LENGTH_LONG).show();

                }

            }
        });

        afternoonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    alarmSharedPreference.setData(3, true);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm on for afternoon", Toast.LENGTH_LONG).show();

                }else{

                    alarmSharedPreference.setData(3, false);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm off for afternoon", Toast.LENGTH_LONG).show();

                }

            }
        });

        eveningSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    alarmSharedPreference.setData(4, true);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm on for evening", Toast.LENGTH_LONG).show();

                }else{

                    alarmSharedPreference.setData(4, false);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm off for evening", Toast.LENGTH_LONG).show();

                }

            }
        });

        nightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    alarmSharedPreference.setData(5, true);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm on for night", Toast.LENGTH_LONG).show();

                }else{

                    alarmSharedPreference.setData(5, false);
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm off for night", Toast.LENGTH_LONG).show();

                }

            }
        });

        return view;
    }

    private void initializeTimerTextViews() {

        morningTime.setText(timeFormat(medicineSharedPreference.getData(1))); // morning
        noonTime.setText(timeFormat(medicineSharedPreference.getData(2))); // noon
        afternoonTime.setText(timeFormat(medicineSharedPreference.getData(3))); // afternoon
        eveningTime.setText(timeFormat(medicineSharedPreference.getData(4))); // evening
        nightTime.setText(timeFormat(medicineSharedPreference.getData(5))); // night

    }

    private void initializeTimerTextSwitches() {

        morningSwitch.setChecked(alarmSharedPreference.getData(1)); // morning
        noonSwitch.setChecked(alarmSharedPreference.getData(2)); // noon
        afternoonSwitch.setChecked(alarmSharedPreference.getData(3)); // afternoon
        eveningSwitch.setChecked(alarmSharedPreference.getData(4)); // evening
        nightSwitch.setChecked(alarmSharedPreference.getData(5)); // night

    }


    private String timeFormat(String time){

        String timeFormat = null;

        String[] split = time.split(" : ", 5);

        if(split[2].equals("PM")){

            if(Integer.parseInt(split[0]) >= 10){
                timeFormat = Integer.parseInt(split[0]) % 12+" : ";
            }else{
                timeFormat = "0"+Integer.parseInt(split[0]) % 12+" : ";
            }

            if(Integer.parseInt(split[1])>=10){
                timeFormat = timeFormat+Integer.parseInt(split[1])+" : PM";
            }else{
                timeFormat = timeFormat+"0"+Integer.parseInt(split[1])+" : PM";
            }

        }else{

            if(Integer.parseInt(split[0]) >= 10){
                timeFormat = Integer.parseInt(split[0]) % 12+" : ";
            }else{
                timeFormat = "0"+Integer.parseInt(split[0]) % 12+" : ";
            }

            if(Integer.parseInt(split[1])>=10){
                timeFormat = timeFormat+Integer.parseInt(split[1])+" : AM";
            }else{
                timeFormat = timeFormat+"0"+Integer.parseInt(split[1])+" : AM";
            }

        }

        return timeFormat;

    }

    private void setTimer(int sessionId) {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog timePicker;

        timePicker = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                Calendar calendar;
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                calendar.set(Calendar.MINUTE, selectedMinute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                alarm.updateAlarm(calendar, sessionId);

                changeTime(sessionId, selectedHour, selectedMinute);

            }
        }, hour, minute, false);//Yes 24 hour time

        timePicker.setTitle("Pick Time");
        timePicker.show();

    }

    private void changeTime(int sessionId, int selectedHour, int selectedMinute) {

        String time;

        if(selectedHour>12){

            if(selectedHour>=22){
                time = selectedHour%12+" : ";
            }else{
                time = "0"+selectedHour%12+" : ";
            }

            if(selectedMinute>=10){
                time = time+selectedMinute+" : PM";
            }else{
                time = time+"0"+selectedMinute+" : PM";
            }

        }else{

            if(selectedHour>=10){
                time = selectedHour%12+" : ";
            }else{
                time = "0"+selectedHour%12+" : ";
            }

            if(selectedMinute>=10){
                time = time+selectedMinute+" : AM";
            }else{
                time = time+"0"+selectedMinute+" : AM";
            }

        }

        switch(sessionId){

            case 1 :
                morningTime.setText(time); // morning
                medicineSharedPreference.setData(1, time); // shared preference
                break;

            case 2 :
                noonTime.setText(time); // noob
                medicineSharedPreference.setData(2, time); // shared preference
                break;

            case 3 :
                afternoonTime.setText(time); // afternoon
                medicineSharedPreference.setData(3, time); // shared preference
                break;

            case 4 :
                eveningTime.setText(time); // evening
                medicineSharedPreference.setData(4, time); // shared preference
                break;

            case 5 :
                nightTime.setText(time); // night
                medicineSharedPreference.setData(5, time); // shared preference
                break;

        }

    }


}