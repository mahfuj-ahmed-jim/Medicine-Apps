package com.ai.medicinereminder.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm {

    private Context context;

    public Alarm(Context context) {
        this.context = context;
    }

    public void setAlarm(Calendar calendar, int sessionId){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent;

        /*if(sessionId == 6){ // for reset
            intent = new Intent(context, MedicineAlarmReceiver.class);
        }else{ // for session alarm
            intent = new Intent(context, AlarmReceiver.class);
        }*/

        intent = new Intent(context, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }else{
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

    public void updateAlarm(Calendar calendar, int sessionId){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId, intent, 0);
        alarmManager.cancel(pendingIntent);

        setAlarm(calendar, sessionId);

    }

    public void cancelAlarm(int sessionId){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId, intent, 0);
        alarmManager.cancel(pendingIntent);

    }

}
