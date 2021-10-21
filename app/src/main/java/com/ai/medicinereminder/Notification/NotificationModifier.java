package com.ai.medicinereminder.Notification;

import static com.ai.medicinereminder.Notification.NotificationClass.Medicine_Channel;

import android.app.Notification;
import android.content.Context;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ai.medicinereminder.R;

public class NotificationModifier {

    private Context context;

    // notification
    private NotificationManagerCompat notificationManagerCompat;

    public NotificationModifier(Context context) {
        this.context = context;
        // notification
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    public void showNotification(String title, String text){

        Notification notification = new NotificationCompat.Builder(context, Medicine_Channel)
                .setSmallIcon(R.drawable.home)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);

    }

}
