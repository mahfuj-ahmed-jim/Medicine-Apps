package com.ai.medicinereminder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);

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
        viewPagerAdapter.addFragment(settingsFragment, "Settings");

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setCurrentItem(0); // set home as first item

        // set icons for tab layout
        /*tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_search);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_mysearch);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_menu);*/

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