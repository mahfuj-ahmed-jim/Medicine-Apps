package com.ai.medicinereminder.MainActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ai.medicinereminder.Activity.PageActivity;
import com.ai.medicinereminder.Adapter.MedicineRecyclerViewAdapter;
import com.ai.medicinereminder.Constant.MedicineConstant;
import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MedicineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // edit text
    private EditText medicineSearchEditText;
    private CharSequence sequence = "";

    // layout as button
    private LinearLayout addNewMedicineButton;

    // button
    private Button backButton;

    //RecyclerView
    private RecyclerView medicineRecyclerView;
    private List<Medicine> medicineList = new ArrayList<>();
    MedicineRecyclerViewAdapter medicineRecyclerViewAdapter;

    // room database
    private MainDatabase mainDatabase;

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

        // edit text
        medicineSearchEditText = view.findViewById(R.id.editTextId_medicineSearch);

        // room database
        mainDatabase = MainDatabase.getInstance(getContext());

        // layout as button
        addNewMedicineButton = view.findViewById(R.id.linearLayoutId_addNewMedicine);

        // back button
        backButton = view.findViewById(R.id.back_button_id);

        // recycler view
        medicineRecyclerView = view.findViewById(R.id.medicineListView);

        medicineSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                medicineRecyclerViewAdapter.getFilter().filter(s);
                sequence = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // on click listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        addNewMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MedicineConstant medicineConstant = new MedicineConstant();
                medicineConstant.setMedicineId(null);

                Intent intent = new Intent(getActivity().getApplicationContext(), PageActivity.class);
                intent.putExtra(getActivity().getApplicationContext().getString(R.string.activity),
                        getActivity().getApplicationContext().getString(R.string.addMedicine));
                startActivity(intent);

                getActivity().overridePendingTransition(0, 0); //intent animation

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        medicineList = mainDatabase.medicineDao().getMedicineList();

        // we are initializing our adapter class and passing our arraylist to it.
        medicineRecyclerViewAdapter = new MedicineRecyclerViewAdapter(getActivity(), medicineList);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        medicineRecyclerView.setLayoutManager(linearLayoutManager);
        medicineRecyclerView.setAdapter(medicineRecyclerViewAdapter);

        medicineRecyclerViewAdapter.notifyDataSetChanged();

    }
}