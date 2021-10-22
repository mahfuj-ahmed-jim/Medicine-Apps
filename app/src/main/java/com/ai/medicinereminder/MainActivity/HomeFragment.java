package com.ai.medicinereminder.MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    //RecyclerView
    private RecyclerView medicineRecyclerView;
    private List <Medicine> medicineList = new ArrayList<>();
    HomeRecyclerViewAdapter recyclerViewAdapter;

    //TextView
    private TextView userName,homeQuote;

    //EditText
    private EditText medicineSearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainDatabase = MainDatabase.getInstance(getContext());

        medicineRecyclerView = view.findViewById(R.id.medicineListView);
        userName = view.findViewById(R.id.home_userName);
        homeQuote = view.findViewById(R.id.home_quotes);
        medicineSearch = view.findViewById(R.id.home_medicineSearch);

        medicineList = new ArrayList<>();

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
        recyclerViewAdapter = new HomeRecyclerViewAdapter(getActivity(), medicineList);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        medicineRecyclerView.setLayoutManager(linearLayoutManager);
        medicineRecyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.notifyDataSetChanged();

    }
}