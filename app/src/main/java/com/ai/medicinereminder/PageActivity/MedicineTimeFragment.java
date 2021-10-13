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
import android.widget.TimePicker;
import android.widget.Toast;

import com.ai.medicinereminder.Alarm.Alarm;
import com.ai.medicinereminder.Alarm.AlarmReceiver;
import com.ai.medicinereminder.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MedicineTimeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

        morningLayout = view.findViewById(R.id.constraintId_morning);
        noonLayout = view.findViewById(R.id.constraintId_noon);
        afternoonLayout = view.findViewById(R.id.constraintId_afternoon);
        eveningLayout = view.findViewById(R.id.constraintId_evening);
        nightLayout = view.findViewById(R.id.constraintId_night);

        morningLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimer(1);
            }
        });

        return view;
    }

    private void setTimer(int id) {

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

                //calendar.add(Calendar.DATE, 1);

                Log.d("Alarm", calendar.getTime()+" "+calendar.getTimeInMillis());

                int sessionId = 1;

                Alarm alarm = new Alarm(getActivity().getApplicationContext());
                alarm.setAlarm(calendar, sessionId);

            }
        }, hour, minute, false);//Yes 24 hour time

        timePicker.setTitle("Pick Time");
        timePicker.show();

    }

}