package com.ai.medicinereminder.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ai.medicinereminder.Activity.PageActivity;
import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.Notification.NotificationModifier;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;

import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    // room database
    private MainDatabase mainDatabase;
    // time
    private int currentTime;
    private int morning, noon, afternoon, evening, night;

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;
    private String morningTime, noonTime, afternoonTime, eveningTime, nightTime;

    // notification
    NotificationModifier notificationModifier;

    @Override
    public void onReceive(Context context, Intent intent) {

        // room database
        mainDatabase = MainDatabase.getInstance(context);

        // shared preference
        medicineSharedPreference = new MedicineSharedPreference(context);
        this.morningTime = medicineSharedPreference.getData(1);
        this.noonTime = medicineSharedPreference.getData(2);
        this.afternoonTime = medicineSharedPreference.getData(3);
        this.eveningTime = medicineSharedPreference.getData(4);
        this.nightTime = medicineSharedPreference.getData(5);

        // convert time to minutes
        String morningText [] = morningTime.split(" : ");
        String noonText [] = noonTime.split(" : ");
        String afternoonText [] = afternoonTime.split(" : ");
        String eveningText [] = eveningTime.split(" : ");
        String nightText [] = nightTime.split(" : ");

        this.morning = Integer.parseInt(morningText[0])*60 + Integer.parseInt(morningText[1]) +
                (morningText[2].equals("PM")? 720:0);
        this.noon = Integer.parseInt(noonText[0])*60 + Integer.parseInt(noonText[1]) +
                (noonText[2].equals("PM")? 720:0);
        this.afternoon = Integer.parseInt(afternoonText[0])*60 + Integer.parseInt(afternoonText[1]) +
                (afternoonText[2].equals("PM")? 720:0);
        this.evening = Integer.parseInt(eveningText[0])*60 + Integer.parseInt(eveningText[1]) +
                (eveningText[2].equals("PM")? 720:0);
        this.night = Integer.parseInt(nightText[0])*60 + Integer.parseInt(nightText[1]) +
                (nightText[2].equals("PM")? 720:0);

        Date currentTime = Calendar.getInstance().getTime();
        this.currentTime = currentTime.getHours()*60 + currentTime.getMinutes();
        // convert time to minutes

        if(this.currentTime == this.morning){

            for(Medicine medicine : mainDatabase.medicineDao().getMedicineList()){

                if(medicine.isMorning()){

                    Intent in = new Intent(context, PageActivity.class);
                    in.putExtra(context.getString(R.string.activity),
                            context.getString(R.string.alarm));
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);

                    notificationModifier = new NotificationModifier(context);
                    notificationModifier.showNotification("Morning Alarm", "Alarm");

                    break;

                }

            }

        }else if(this.currentTime == this.noon){



        }else if(this.currentTime == this.afternoon){



        }else if(this.currentTime == this.evening){



        }else if(this.currentTime == this.night){



        }

    }

}