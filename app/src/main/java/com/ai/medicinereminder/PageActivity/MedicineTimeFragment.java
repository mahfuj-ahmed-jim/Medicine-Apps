package com.ai.medicinereminder.PageActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ai.medicinereminder.Alarm.Alarm;
import com.ai.medicinereminder.R;
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

    // alarm
    private Alarm alarm;

    // share preference for time
    private MedicineSharedPreference medicineSharedPreference;

    // textViews
    private TextView morningTime, noonTime, afternoonTime,eveningTime, nightTime;

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

        // layouts
        morningLayout = view.findViewById(R.id.constraintId_morning);
        noonLayout = view.findViewById(R.id.constraintId_noon);
        afternoonLayout = view.findViewById(R.id.constraintId_afternoon);
        eveningLayout = view.findViewById(R.id.constraintId_evening);
        nightLayout = view.findViewById(R.id.constraintId_night);

        // set up timer textViews
        medicineSharedPreference = new MedicineSharedPreference(getActivity().getApplicationContext());
        initializeTimerTextViews();

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

        return view;
    }

    private void initializeTimerTextViews() {

        morningTime.setText(medicineSharedPreference.getData(1)); // morning
        noonTime.setText(medicineSharedPreference.getData(2)); // noon
        afternoonTime.setText(medicineSharedPreference.getData(3)); // afternoon
        eveningTime.setText(medicineSharedPreference.getData(4)); // evening
        nightTime.setText(medicineSharedPreference.getData(5)); // night

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
                time = selectedHour%12+" : "+selectedMinute+" : PM";
            }else{
                time = "0"+selectedHour%12+" : "+selectedMinute+" : PM";
            }

        }else{

            if(selectedHour>=10){
                time = selectedHour+" : "+selectedMinute+" : AM";
            }else{
                time = "0"+selectedHour+" : "+selectedMinute+" : AM";
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