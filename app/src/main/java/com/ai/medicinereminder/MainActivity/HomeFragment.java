package com.ai.medicinereminder.MainActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ai.medicinereminder.PageActivity.Model_home_card;
import com.ai.medicinereminder.PageActivity.home_Card_Adapter;
import com.ai.medicinereminder.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView MedicineCard;
    private ArrayList<Model_home_card> cardArrayList;
    home_Card_Adapter cardAdapter;


    private TextView userName,homeQuote;
    private EditText medicineSearch;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        MedicineCard = view.findViewById(R.id.medicineListView);
        userName = view.findViewById(R.id.home_userName);
        homeQuote = view.findViewById(R.id.home_quotes);
        medicineSearch = view.findViewById(R.id.home_medicineSearch);




        return view;
    }
}