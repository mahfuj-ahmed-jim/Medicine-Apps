package com.ai.medicinereminder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;

public class SplashActivity extends AppCompatActivity {

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        medicineSharedPreference = new MedicineSharedPreference(getApplicationContext());

        String time = medicineSharedPreference.getData(1);

        if(time == null){

            medicineSharedPreference.setData(1, "08 : 00 AM"); // morning
            medicineSharedPreference.setData(2, "01 : 00 PM"); // noon
            medicineSharedPreference.setData(3, "04 : 30 AM"); // afternoon
            medicineSharedPreference.setData(4, "06 : 30 AM"); // evening
            medicineSharedPreference.setData(5, "09 : 00 AM"); // night

        }else{

        }

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }
}