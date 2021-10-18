package com.ai.medicinereminder.MainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ai.medicinereminder.Activity.PageActivity;
import com.ai.medicinereminder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MedicineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // floating action button
    private FloatingActionButton floatingActionButton;

    // layout as button
    private LinearLayout addNewMedicineButton;

    // button
    private Button backButton;

    public MedicineFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MedicineFragment newInstance(String param1, String param2) {
        MedicineFragment fragment = new MedicineFragment();
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
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        // floation action button
        floatingActionButton = view.findViewById(R.id.floatingActionButtonId);

        // layout as button
        addNewMedicineButton = view.findViewById(R.id.linearLayoutId_addNewMedicine);

        // back button
        backButton = view.findViewById(R.id.back_button_id);

        // on click listeners
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), PageActivity.class);
                intent.putExtra(getActivity().getApplicationContext().getString(R.string.activity),
                        getActivity().getApplicationContext().getString(R.string.addMedicine));
                startActivity(intent);

                getActivity().overridePendingTransition(0, 0); //intent animation

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        addNewMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), PageActivity.class);
                intent.putExtra(getActivity().getApplicationContext().getString(R.string.activity),
                        getActivity().getApplicationContext().getString(R.string.addMedicine));
                startActivity(intent);

                getActivity().overridePendingTransition(0, 0); //intent animation

            }
        });

        return view;
    }
}