package com.ai.medicinereminder.Alarm;

import static android.content.Context.POWER_SERVICE;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import com.ai.medicinereminder.Activity.PageActivity;
import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.Database.MedicineHistory;
import com.ai.medicinereminder.Notification.NotificationModifier;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.SharedPreference.AlarmSharedPreference;
import com.ai.medicinereminder.SharedPreference.MedicineSharedPreference;

import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    // context
    private Context context;

    // calendar
    private Calendar calendar;

    // alarm
    private Alarm alarm;

    // room database
    private MainDatabase mainDatabase;

    // time
    private int currentTime;
    private int morning, noon, afternoon, evening, night, reset;

    // shared preference
    private MedicineSharedPreference medicineSharedPreference;
    private AlarmSharedPreference alarmSharedPreference;
    private String morningTime, noonTime, afternoonTime, eveningTime, nightTime, resetTime;

    // notification
    NotificationModifier notificationModifier;

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
        alarmSharedPreference = new AlarmSharedPreference(context);

        this.morningTime = medicineSharedPreference.getData(1);
        this.noonTime = medicineSharedPreference.getData(2);
        this.afternoonTime = medicineSharedPreference.getData(3);
        this.eveningTime = medicineSharedPreference.getData(4);
        this.nightTime = medicineSharedPreference.getData(5);
        this.resetTime = medicineSharedPreference.getData(6);

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
        this.reset = Integer.parseInt(nightText[0])*60 + Integer.parseInt(nightText[1]) +
                (nightText[2].equals("PM")? 720:0);

        Date currentTime = Calendar.getInstance().getTime();
        this.currentTime = currentTime.getHours()*60 + currentTime.getMinutes();
        // convert time to minutes

        if(this.currentTime == this.morning){

            if(alarmSharedPreference.getData(1)){

                for(Medicine medicine : mainDatabase.medicineDao().getMedicineList()){

                    if(medicine.isMorning()){

                        showNotification("Medicine TIme", "Time for morning medicines");
                        break;

                    }

                }

            }

        }else if(this.currentTime == this.noon){

            if(alarmSharedPreference.getData(2)){

                for(Medicine medicine : mainDatabase.medicineDao().getMedicineList()){

                    if(medicine.isNoon()){

                        showNotification("Medicine TIme", "Time for noon medicines");
                        break;

                    }

                }

            }

        }else if(this.currentTime == this.afternoon){

            if(alarmSharedPreference.getData(3)){

                for(Medicine medicine : mainDatabase.medicineDao().getMedicineList()){

                    if(medicine.isAfternoon()){

                        showNotification("Medicine TIme", "Time for afternoon medicines");
                        break;

                    }

                }

            }

        }else if(this.currentTime == this.evening){

            if(alarmSharedPreference.getData(4)){

                for(Medicine medicine : mainDatabase.medicineDao().getMedicineList()){

                    if(medicine.isEvening()){

                        showNotification("Medicine TIme", "Time for evening medicines");
                        break;

                    }

                }

            }

        }else if(this.currentTime == this.night){

            if (alarmSharedPreference.getData(5)){

                for(Medicine medicine : mainDatabase.medicineDao().getMedicineList()){

                    if(medicine.isNight()){

                        showNotification("Medicine TIme", "Time for night medicines");
                        break;

                    }

                }

            }

        }else{

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

    public void showNotification(String title, String text){

        notificationModifier = new NotificationModifier(context);
        notificationModifier.showNotification(title, text);

        newPage();

    }

    public void newPage(){

        //WAKE UP DEVICE
        WakeLocker.acquire(context);

        Intent in = new Intent(context, PageActivity.class);
        in.putExtra(context.getString(R.string.activity),
                context.getString(R.string.alarm));
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);

        Log.d("Verify", "Alarm");

        WakeLocker.release();

    }

    public void setAlarm(){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent;

        /*if(currentTime == morning || currentTime == noon || currentTime == afternoon ||
                currentTime == evening || currentTime == night
        ){ // for session alarm
            intent = new Intent(context, AlarmReceiver.class);
        }else{ // for reset
            intent = new Intent(context, MedicineAlarmReceiver.class);
        }*/

        intent = new Intent(context, AlarmReceiver.class);
        
        int sessionId = 0;

        if(currentTime == morning){

            // for reset
            calendar.set(Calendar.HOUR_OF_DAY, morning/60);
            calendar.set(Calendar.MINUTE, morning%60);
            sessionId = 1;

        }else if(currentTime == noon){

            // for reset
            calendar.set(Calendar.HOUR_OF_DAY, noon/60);
            calendar.set(Calendar.MINUTE, noon%60);
            sessionId = 2;

        }else if(currentTime == afternoon){

            // for reset
            calendar.set(Calendar.HOUR_OF_DAY, afternoon/60);
            calendar.set(Calendar.MINUTE, afternoon%60);
            sessionId = 3;

        }else if(currentTime == evening){

            // for reset
            calendar.set(Calendar.HOUR_OF_DAY, evening/60);
            calendar.set(Calendar.MINUTE, evening%60);
            sessionId = 4;

        }else if(currentTime == night){

            // for reset
            calendar.set(Calendar.HOUR_OF_DAY, night/60);
            calendar.set(Calendar.MINUTE, night%60);
            sessionId = 5;

        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, sessionId, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }else{
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

}