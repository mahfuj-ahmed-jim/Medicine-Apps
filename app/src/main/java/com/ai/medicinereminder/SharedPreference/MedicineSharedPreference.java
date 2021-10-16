package com.ai.medicinereminder.SharedPreference;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;

import com.ai.medicinereminder.R;

public class MedicineSharedPreference {

    private Activity activity;

    // shared preference
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public MedicineSharedPreference(Activity activity) {
        this.activity = activity;
        this.preferences = activity.getSharedPreferences(String.valueOf(R.string.TimingInformation), MODE_PRIVATE); //initialize shared Preference
    }

    public void setData(int sessionId, String time){

        editor = preferences.edit();

        switch(sessionId){

            case 1 :
                editor.putString(String.valueOf(R.string.morningTime), time); // write morning time
                return;

            case 2 :
                editor.putString(String.valueOf(R.string.noonTime), time); // write noon time
                return;

            case 3 :
                editor.putString(String.valueOf(R.string.afternoonTime), time); // write afternoon time
                return;

            case 4 :
                editor.putString(String.valueOf(R.string.eveningTime), time); // write evening time
                return;

            case 5 :
                editor.putString(String.valueOf(R.string.nightTime), time); // write night time
                return;

        }

        editor.commit(); // write to shared preference

    }

    public String getData(int sessionId){

        String time = null;

        // start
        if(preferences.contains(String.valueOf(R.string.morningTime))){ // read from shared preference

            if(sessionId == 1){
                time = preferences.getString(String.valueOf(R.string.morningTime), null);
            }else if(sessionId == 2){
                time = preferences.getString(String.valueOf(R.string.noonTime), null);
            }else if(sessionId == 3){
                time = preferences.getString(String.valueOf(R.string.afternoonTime), null);
            }else if(sessionId == 4){
                time = preferences.getString(String.valueOf(R.string.eveningTime), null);
            }else if(sessionId == 5){
                time = preferences.getString(String.valueOf(R.string.nightTime), null);
            }

        }else{

        }
        // end

        return time;

    }

}
