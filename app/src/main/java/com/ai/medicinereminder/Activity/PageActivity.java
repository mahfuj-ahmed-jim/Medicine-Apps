package com.ai.medicinereminder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ai.medicinereminder.PageActivity.MedicineTimeFragment;
import com.ai.medicinereminder.R;

public class PageActivity extends AppCompatActivity {

    // fragment layout
    private Fragment selectedFragment = null;
    private static String FRAGMENT_TAG;

    private MedicineTimeFragment medicineTimeFragment = new MedicineTimeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        String activity = getIntent().getStringExtra(getApplicationContext().getString(R.string.activity));

        if(activity.equals(getApplicationContext().getString(R.string.medicineTime))){

            //set fragment (log in fragment)
            selectedFragment = medicineTimeFragment;
            FRAGMENT_TAG = getApplicationContext().getString(R.string.medicineTime);

        }

        // shift to another fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, selectedFragment, FRAGMENT_TAG).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0); //intent animation
    }
}