package com.ai.medicinereminder.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.MedicineHistory;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;

import java.util.Calendar;
import java.util.Date;

public class MedicineAlarmReceiver extends BroadcastReceiver {

    // context
    private Context context;

    // room database
    private MainDatabase mainDatabase;

    // calendar
    private Calendar calendar;

    // alarm
    private Alarm alarm;

    // time
    private int morning;
    private String morningTime;

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;

    @Override
    public void onReceive(Context context, Intent intent) {

        // context
        this.context = context;

        // alarm
        alarm = new Alarm(context);

        // calendar
        calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // room database
        mainDatabase = MainDatabase.getInstance(context);

        // shared preference
        medicineSharedPreference = new MedicineSharedPreference(context);

        this.morningTime = medicineSharedPreference.getData(1);

        // convert time to minutes
        String morningText [] = morningTime.split(" : ");

        this.morning = Integer.parseInt(morningText[0])*60 + Integer.parseInt(morningText[1]) +
                (morningText[2].equals("PM")? 720:0);

        // convert time to minutes

        // reset for next alarm
        calendar.set(Calendar.HOUR_OF_DAY, this.morning/60);
        calendar.set(Calendar.MINUTE, this.morning%60);
        alarm.cancelAlarm(6);
        alarm.setAlarm(calendar, 1);

        for(MedicineHistory medicineHistory : mainDatabase.medicineHistoryDao().getMedicineHistoryList()){

            medicineHistory.setMorning(false);
            medicineHistory.setNoon(false);
            medicineHistory.setAfternoon(false);
            medicineHistory.setEvening(false);
            medicineHistory.setNight(false);

            // set all the medicine not eaten for the next day
            mainDatabase.medicineHistoryDao().updateMedicineHistory(medicineHistory);

        }

    }
}