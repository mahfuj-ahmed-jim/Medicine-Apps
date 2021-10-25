package com.ai.medicinereminder.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ai.medicinereminder.Notification.NotificationModifier;

public class AlarmReceiver extends BroadcastReceiver {

    NotificationModifier notificationModifier;

    @Override
    public void onReceive(Context context, Intent intent) {

        /*notificationModifier = new NotificationModifier(context);
        notificationModifier.showNotification("Alarm", "Alarm");*/

    }
}