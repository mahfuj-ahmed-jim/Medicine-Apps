package com.ai.medicinereminder.Alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends Service
{
    AlarmReceiver alarm = new AlarmReceiver();
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        alarm.setAlarm();
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.setAlarm();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}