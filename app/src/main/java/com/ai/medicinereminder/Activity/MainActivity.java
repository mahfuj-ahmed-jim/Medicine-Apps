package com.ai.medicinereminder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ai.medicinereminder.MainActivity.HistoryFragment;
import com.ai.medicinereminder.PageActivity.AddMedicineFragment;
import com.ai.medicinereminder.MainActivity.HomeFragment;
import com.ai.medicinereminder.MainActivity.MedicineFragment;
import com.ai.medicinereminder.MainActivity.SettingsFragment;
import com.ai.medicinereminder.R;
import com.ai.medicinereminder.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    // tab layout
    private TabLayout tabLayout;
    public static ViewPager viewPager;
    public static ViewPagerAdapter viewPagerAdapter; // view adapter
    private int POSITION [] = {0, 0};

    // fragments
    private HomeFragment homeFragment = new HomeFragment();
    private MedicineFragment medicineFragment = new MedicineFragment();
    private HistoryFragment historyFragment = new HistoryFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            // status bar color
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(getResources().getColor(R.color.second_color));
            }
        }catch (Exception e){
        }

        // tab layout
        tabLayout = findViewById(R.id.tabLayoutId);
        viewPager = findViewById(R.id.viewPagerId);

        // initialize
        setTabLayout();

        // tabLayout select view color changer
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);

                        POSITION [0] = POSITION [1];
                        POSITION [1] = tab.getPosition();

                        if(tab.getPosition() == 0){

                            try{
                                // status bar color
                                Window window = getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    window.setStatusBarColor(getResources().getColor(R.color.second_color));
                                }
                            }catch (Exception e){
                            }

                        }else{

                            try{
                                // status bar color
                                Window window = getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    window.setStatusBarColor(getResources().getColor(R.color.white));
                                }
                            }catch (Exception e){
                            }

                        }

                        // change icon color
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.main_color);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);

                        // change icon color
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.lightModeThirdText_color);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

    }

    private void setTabLayout() {

        tabLayout.setupWithViewPager(viewPager); // setting view page

        // add views for each category
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(homeFragment, "Home");
        viewPagerAdapter.addFragment(medicineFragment, "Medicine");
        viewPagerAdapter.addFragment(historyFragment, "History");
        viewPagerAdapter.addFragment(settingsFragment, "More");

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setCurrentItem(0); // set home as first item

        // set icons for tab layout
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.medicine);
        tabLayout.getTabAt(2).setIcon(R.drawable.history);
        tabLayout.getTabAt(3).setIcon(R.drawable.settings);

        // initial color
        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.main_color);
        int tabIconColor2 = ContextCompat.getColor(getApplicationContext(), R.color.lightModeThirdText_color);

        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(tabIconColor2, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(tabIconColor2, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(tabIconColor2, PorterDuff.Mode.SRC_IN);

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

        if(POSITION [1] == 0){
            super.onBackPressed();
        }else{
            if(POSITION [0] == POSITION [1]){
                viewPager.setCurrentItem(0);
            }else{
                viewPager.setCurrentItem(POSITION [0]);
                POSITION [0] = POSITION [1];
            }
        }

    }

}