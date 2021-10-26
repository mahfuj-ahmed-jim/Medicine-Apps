package com.ai.medicinereminder.SharedPreference;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.ai.medicinereminder.R;

public class AlarmSharedPreference {

    private Context context;

    // shared preference
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public AlarmSharedPreference(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(String.valueOf(R.string.AlarmInformation), MODE_PRIVATE); //initialize shared Preference

    }

    public void setData(int sessionId, boolean notification){

        editor = preferences.edit();

        switch(sessionId){

            case 1 :
                editor.putBoolean(String.valueOf(R.string.morningTime), notification); // write morning notification
                break;

            case 2 :
                editor.putBoolean(String.valueOf(R.string.noonTime), notification); // write noon notification
                break;

            case 3 :
                editor.putBoolean(String.valueOf(R.string.afternoonTime), notification); // write afternoon notification
                break;

            case 4 :
                editor.putBoolean(String.valueOf(R.string.eveningTime), notification); // write evening notification
                break;

            case 5 :
                editor.putBoolean(String.valueOf(R.string.nightTime), notification); // write night notification
                break;

        }

        editor.commit(); // write to shared preference

    }

    public boolean getData(int sessionId){

        boolean notification = true;

        // start
        if(preferences.contains(String.valueOf(R.string.morningTime))){ // read from shared preference

            switch(sessionId){

                case 1 :
                    notification = preferences.getBoolean(String.valueOf(R.string.morningTime), true); // morning
                    break;

                case 2 :
                    notification = preferences.getBoolean(String.valueOf(R.string.noonTime), true); // noon
                    break;

                case 3 :
                    notification = preferences.getBoolean(String.valueOf(R.string.afternoonTime), true); // afternoon
                    break;

                case 4 :
                    notification = preferences.getBoolean(String.valueOf(R.string.eveningTime), true); // evening
                    break;

                case 5 :
                    notification = preferences.getBoolean(String.valueOf(R.string.nightTime), true); // night
                    break;

            }

        }else{

        }
        // end

        return notification;

    }

}
