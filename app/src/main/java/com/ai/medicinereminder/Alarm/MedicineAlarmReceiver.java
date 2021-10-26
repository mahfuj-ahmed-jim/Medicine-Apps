package com.ai.medicinereminder.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.MedicineHistory;

public class MedicineAlarmReceiver extends BroadcastReceiver {

    // room database
    private MainDatabase mainDatabase;

    @Override
    public void onReceive(Context context, Intent intent) {

        // room database
        mainDatabase = MainDatabase.getInstance(context);

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