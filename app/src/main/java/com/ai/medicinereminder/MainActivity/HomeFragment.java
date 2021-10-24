package com.ai.medicinereminder.MainActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ai.medicinereminder.Adapter.HomeRecyclerViewAdapter;
import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // room database
    private MainDatabase mainDatabase;

    // nested scroll view
    private NestedScrollView nestedScrollView;

    //RecyclerView
    private RecyclerView medicineRecyclerView;
    private List <Medicine> medicineList = new ArrayList<>();
    HomeRecyclerViewAdapter recyclerViewAdapter;

    //TextView
    private TextView userName,homeQuote;

    //EditText
    private ConstraintLayout searchLayout;
    private EditText medicineSearchEditText;
    private CharSequence sequence = "";

    // button
    private Button corssButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        medicineRecyclerView = view.findViewById(R.id.medicineListView);
        userName = view.findViewById(R.id.home_userName);
        homeQuote = view.findViewById(R.id.home_quotes);
        medicineSearchEditText = view.findViewById(R.id.editText_search);
        searchLayout = view.findViewById(R.id.constraintId_search);

        corssButton = view.findViewById(R.id.buttonId_cross);

        medicineList = new ArrayList<>();

        medicineSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recyclerViewAdapter.getFilter().filter(s);
                sequence = s;

                if(medicineSearchEditText.getText().toString().trim().length() == 0){
                    corssButton.setVisibility(View.GONE);
                }else{
                    corssButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        medicineSearchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    nestedScrollView.smoothScrollTo(0, medicineSearchEditText.getBottom()+500);
                }else{

                }

            }
        });

        // on click listeners
        corssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineSearchEditText.setText("");
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineSearchEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(medicineSearchEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        return view;
    }

    public void setRecyclerView(){

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        try{
            mainDatabase = MainDatabase.getInstance(getContext());
            medicineList = mainDatabase.medicineDao().getMedicineList();

            // we are initializing our adapter class and passing our arraylist to it.
            recyclerViewAdapter = new HomeRecyclerViewAdapter(getActivity(), medicineList);

            // in below two lines we are setting layoutmanager and adapter to our recycler view.
            medicineRecyclerView.setLayoutManager(linearLayoutManager);
            medicineRecyclerView.setAdapter(recyclerViewAdapter);

            recyclerViewAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        setRecyclerView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            setRecyclerView();
        }
    }

}