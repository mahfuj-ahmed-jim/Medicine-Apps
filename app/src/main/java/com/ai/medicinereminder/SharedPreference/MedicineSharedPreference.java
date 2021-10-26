package com.ai.medicinereminder.SharedPreference;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.ai.medicinereminder.R;

public class MedicineSharedPreference {

    private Context context;

    // shared preference
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public MedicineSharedPreference(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(String.valueOf(R.string.TimingInformation), MODE_PRIVATE); //initialize shared Preference
    }

    public void setData(int sessionId, String time){

        editor = preferences.edit();

        switch(sessionId){

            case 1 :
                editor.putString(String.valueOf(R.string.morningTime), time); // write morning time
                break;

            case 2 :
                editor.putString(String.valueOf(R.string.noonTime), time); // write noon time
                break;

            case 3 :
                editor.putString(String.valueOf(R.string.afternoonTime), time); // write afternoon time
                break;

            case 4 :
                editor.putString(String.valueOf(R.string.eveningTime), time); // write evening time
                break;

            case 5 :
                editor.putString(String.valueOf(R.string.nightTime), time); // write night time
                break;

        }

        editor.commit(); // write to shared preference

    }

    public String getData(int sessionId){

        String time = null;

        // start
        if(preferences.contains(String.valueOf(R.string.morningTime))){ // read from shared preference

            switch(sessionId){

                case 1 :
                    time = preferences.getString(String.valueOf(R.string.morningTime), null); // morning
                    break;

                case 2 :
                    time = preferences.getString(String.valueOf(R.string.noonTime), null); // noon
                    break;

                case 3 :
                    time = preferences.getString(String.valueOf(R.string.afternoonTime), null); // afternoon
                    break;

                case 4 :
                    time = preferences.getString(String.valueOf(R.string.eveningTime), null); // evening
                    break;

                case 5 :
                    time = preferences.getString(String.valueOf(R.string.nightTime), null); // night
                    break;

            }

        }else{

        }
        // end

        return time;

    }

}
