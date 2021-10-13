package com.ai.medicinereminder.PageActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

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

    // time picker
    private TimePickerDialog timePicker;
    private Calendar calendar;

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

        timePicker = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                calendar.set(Calendar.MINUTE, selectedMinute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

            }
        }, 10, 35, false);//Yes 24 hour time

        timePicker.setTitle("Pick Time");
        timePicker.show();

    }

}