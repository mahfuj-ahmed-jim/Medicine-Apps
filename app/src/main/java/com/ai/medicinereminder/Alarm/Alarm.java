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

        if(sessionId == 6){ // for reset
            intent = new Intent(context, MedicineAlarmReceiver.class);
        }else{ // for session alarm
            intent = new Intent(context, AlarmReceiver.class);
        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId, intent, PendingIntent.FLAG_ONE_SHOT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }else{
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

        Log.d("Verify", sessionId+" Set Alarm");

        Toast.makeText(context, sessionId+" Set Alarm", Toast.LENGTH_LONG).show();

    }

    public void updateAlarm(Calendar calendar, int sessionId){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId, intent, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.cancel(pendingIntent);

        setAlarm(calendar, sessionId);

    }

    public void cancelAlarm(int sessionId){


        Log.d("Verify", sessionId+" Cancel Alarm");

        Toast.makeText(context, sessionId+" Cancel Alarm", Toast.LENGTH_LONG).show();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId, intent, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.cancel(pendingIntent);

    }

}
