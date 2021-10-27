package com.ai.medicinereminder.PageActivity;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ai.medicinereminder.Adapter.AlarmRecyclerViewAdapter;
import com.ai.medicinereminder.Adapter.MedicineRecyclerViewAdapter;
import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.AlarmSharedPreference;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // room database
    private MainDatabase mainDatabase;

    // time
    private int currentTime;
    private int morning, noon, afternoon, evening, night;

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;
    private String morningTime, noonTime, afternoonTime, eveningTime, nightTime;

    // textView
    private TextView titleTextView, checkBoxTextView;

    // button
    private Button updateButton;

    // checkbox
    private CheckBox checkALl;

    //RecyclerView
    private RecyclerView medicineRecyclerView;
    private List<Medicine> medicineList = new ArrayList<>();
    private AlarmRecyclerViewAdapter alarmRecyclerViewAdapter;

    public AlarmFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AlarmFragment newInstance(String param1, String param2) {
        AlarmFragment fragment = new AlarmFragment();
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
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);


        // textView
        titleTextView = view.findViewById(R.id.textViewId_titleText);
        checkBoxTextView = view.findViewById(R.id.textViewId_checkBox);

        // button
        updateButton = view.findViewById(R.id.buttonId_update);

        // checkbox
        checkALl = view.findViewById(R.id.checkboxId_checkAll);

        // recycler view
        medicineRecyclerView = view.findViewById(R.id.recyclerViewId_alarm);

        // set up
        setUpFragment();

        // on click listeners
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarmRecyclerViewAdapter.updateMedicineHistory();
                alarmRecyclerViewAdapter.notifyDataSetChanged();
                getActivity().finish();

            }
        });

        checkALl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    alarmRecyclerViewAdapter.setAllChecked();
                    alarmRecyclerViewAdapter.notifyDataSetChanged();
                }else{
                    alarmRecyclerViewAdapter.setAllUnChecked();
                    alarmRecyclerViewAdapter.notifyDataSetChanged();
                }

            }
        });

        return view;
    }

    private void setUpRecyclerView(int session) {

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        medicineList = mainDatabase.medicineDao().getMedicineList();

        // we are initializing our adapter class and passing our arraylist to it.
        alarmRecyclerViewAdapter = new AlarmRecyclerViewAdapter(getActivity(), medicineList, session);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        medicineRecyclerView.setLayoutManager(linearLayoutManager);
        medicineRecyclerView.setAdapter(alarmRecyclerViewAdapter);

        alarmRecyclerViewAdapter.notifyDataSetChanged();

    }

    private void setUpFragment() {

        // room database
        mainDatabase = MainDatabase.getInstance(getActivity().getApplicationContext());

        // shared preference
        medicineSharedPreference = new MedicineSharedPreference(getActivity().getApplicationContext());

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

        // padding for textViews
        checkBoxTextView.setPadding(0, 0, 0, 0);
        checkBoxTextView.setIncludeFontPadding(false);

        setTime();

    }

    private void setTime(){

        if(currentTime == morning){
            titleTextView.setText("Morning Medicines");
            setUpRecyclerView(1);
        }else if(currentTime == noon){
            titleTextView.setText("Noon Medicines");
            setUpRecyclerView(2);
        }else if(currentTime == afternoon){
            titleTextView.setText("Afternoon Medicines");
            setUpRecyclerView(3);
        }else if(currentTime == evening){
            titleTextView.setText("Evening Medicines");
            setUpRecyclerView(4);
        }else if(currentTime == night){
            titleTextView.setText("Night Medicines");
            setUpRecyclerView(5);
        }

    }

}