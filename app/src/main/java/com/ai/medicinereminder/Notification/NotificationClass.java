package com.ai.medicinereminder.Notification;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationClass extends Application {

    public static final String Medicine_Channel = "MedicineChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel signInChannel = new NotificationChannel(
                    Medicine_Channel
                    , "Medicine"
                    , NotificationManager.IMPORTANCE_HIGH);


            signInChannel.enableVibration(true);
            signInChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(signInChannel);

        }
    }

}
