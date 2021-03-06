package com.ai.medicinereminder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ai.medicinereminder.PageActivity.AddMedicineFragment;
import com.ai.medicinereminder.PageActivity.AlarmFragment;
import com.ai.medicinereminder.PageActivity.MedicineTimeFragment;
import com.ai.medicinereminder.R;

public class PageActivity extends AppCompatActivity {

    // fragment layout
    private Fragment selectedFragment = null;
    private static String FRAGMENT_TAG;

    // fragments
    private AddMedicineFragment addMedicineFragment = new AddMedicineFragment();
    private MedicineTimeFragment medicineTimeFragment = new MedicineTimeFragment();
    private AlarmFragment alarmFragment = new AlarmFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        String activity = getIntent().getStringExtra(getApplicationContext().getString(R.string.activity));

        if(activity.equals(getApplicationContext().getString(R.string.medicineTime))){

            //set fragment (log in fragment)
            selectedFragment = medicineTimeFragment;
            FRAGMENT_TAG = getApplicationContext().getString(R.string.medicineTime);

        }else if(activity.equals(getApplicationContext().getString(R.string.addMedicine))){

            selectedFragment = addMedicineFragment;
            FRAGMENT_TAG = getApplicationContext().getString(R.string.addMedicine);

        }else if(activity.equals(getApplicationContext().getString(R.string.alarm))){

            selectedFragment = alarmFragment;
            FRAGMENT_TAG = getApplicationContext().getString(R.string.alarm);

        }

        // shift to another fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, selectedFragment, FRAGMENT_TAG).commit();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0); //intent animation
    }

}