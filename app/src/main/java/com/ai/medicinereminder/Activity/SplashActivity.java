package com.ai.medicinereminder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ai.medicinereminder.Alarm.Alarm;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;

import java.util.Calendar;

public class SplashActivity extends AppCompatActivity {

    // calendar
    private Calendar calendar;

    // alarm
    private Alarm alarm;

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // alarm
        alarm = new Alarm(getApplicationContext());

        // calendar
        calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // shared preference
        medicineSharedPreference = new MedicineSharedPreference(getApplicationContext());

        String time = medicineSharedPreference.getData(1);

        if(time == null){

            medicineSharedPreference.setData(1, "08 : 00 : AM"); // morning
            medicineSharedPreference.setData(2, "01 : 00 : PM"); // noon
            medicineSharedPreference.setData(3, "04 : 30 : PM"); // afternoon
            medicineSharedPreference.setData(4, "06 : 30 : PM"); // evening
            medicineSharedPreference.setData(5, "09 : 00 : PM"); // night

            // morning
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            alarm.setAlarm(calendar, 1);

            // noon
            calendar.set(Calendar.HOUR_OF_DAY, 13);
            calendar.set(Calendar.MINUTE, 0);
            alarm.setAlarm(calendar, 2);

            // afternoon
            calendar.set(Calendar.HOUR_OF_DAY, 16);
            calendar.set(Calendar.MINUTE, 30);
            alarm.setAlarm(calendar, 3);

            // evening
            calendar.set(Calendar.HOUR_OF_DAY, 18);
            calendar.set(Calendar.MINUTE, 30);
            alarm.setAlarm(calendar, 4);

            // night
            calendar.set(Calendar.HOUR_OF_DAY, 21);
            calendar.set(Calendar.MINUTE, 0);
            alarm.setAlarm(calendar, 5);

            // for reset
            calendar.set(Calendar.HOUR_OF_DAY, 21);
            calendar.set(Calendar.MINUTE, 48);
            alarm.setAlarm(calendar, 6);


        }else{

        }

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }
}