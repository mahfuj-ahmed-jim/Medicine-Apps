package com.ai.medicinereminder.PageActivity;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ai.medicinereminder.R;

public class MedicineTimeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

            }
        });

        return view;
    }
}