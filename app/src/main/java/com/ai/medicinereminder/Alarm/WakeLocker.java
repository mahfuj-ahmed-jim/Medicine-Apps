package com.ai.medicinereminder.Alarm;

import android.content.Context;
import android.os.PowerManager;

//WAKES UP DEVICE IF PHONE'S SCREEN LOCKED
public abstract class WakeLocker {

    private static PowerManager.WakeLock wakeLock;

    public static void acquire(Context ctx) {

        //if (wakeLock != null) wakeLock.release();

        PowerManager pm = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "myapp:WAKE_LOCK_TAG");
        wakeLock.acquire();

    }

    public static void release() {
        if (wakeLock != null) wakeLock.release(); wakeLock = null;
    }

}