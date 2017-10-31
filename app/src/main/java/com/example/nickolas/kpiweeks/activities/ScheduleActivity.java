package com.example.nickolas.kpiweeks.activities;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nickolas.kpiweeks.R;
import com.example.nickolas.kpiweeks.fragments.WeekFragment;
import com.example.nickolas.kpiweeks.utils.DayInformationUtil;
import com.example.nickolas.kpiweeks.utils.SharedPreferenceUtils;
import com.example.nickolas.kpiweeks.widgets.FragmentChanger;
import com.example.nickolas.kpiweeks.widgets.holders.MyToolbarHolder;

import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity implements FragmentChanger {

    MyToolbarHolder myToolbar;
    private Bool firstInit = new Bool();
    private Bool secondInit = new Bool();
    WeekFragment fragment1;
    WeekFragment fragment2;
    String name;
    Fragment currentFragment;
    @SuppressLint("StaticFieldLeak")
    public static SharedPreferenceUtils sharedPreferenceUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
        myToolbar = new MyToolbarHolder(findViewById(android.R.id.content));
        myToolbar.getButtonText().setText("Вийти");
        myToolbar.getBackButton().setOnClickListener(view -> {
            firstInit.b = false;
            secondInit.b = false;
            deletePreferences();
            startSearch();
        });
        loadPreferences();
        if (Objects.equals(name, "")) {
            startSearch();
        } else {
            startAction();
        }
    }

    private void startSearch() {
        Intent intent = new Intent(this, GroupSearch.class);
        startActivityForResult(intent, 1);
    }

    private void startAction() {
        myToolbar.getTitle().setText(name.toUpperCase());
        fragment1 = WeekFragment.newInstance(0, name, this);
        fragment2 = WeekFragment.newInstance(1, name, this);
        myToolbar.getSwitch().setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b)
                changeFragment(firstInit, fragment1);
            else
                changeFragment(secondInit, fragment2);
        });
        int currentWeek = new DayInformationUtil().getWeekNumber();
        if (currentWeek == 1) {
            changeFragment(firstInit, fragment1);
            fragment1.scroll = true;
        } else {
            myToolbar.getSwitch().performClick();
            fragment2.scroll = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
        }
        savePreferences();
        startAction();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void changeFragment(Bool init, android.app.Fragment fragment) {
        FragmentTransaction fragT = getFragmentManager().beginTransaction();
        if (!init.b) {
            fragT.add(R.id.frame, fragment).commit();
            init.b = true;
        } else {
            fragT.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    @SuppressLint("ApplySharedPref")
    private void savePreferences() {
        sharedPreferenceUtils.setValue("name", name);
    }

    private void loadPreferences() {
        name = sharedPreferenceUtils.getStringValue("name", "");
    }

    private void deletePreferences() {
        sharedPreferenceUtils.clear();
    }

    @Override
    public void change() {
        myToolbar.getSwitch().performClick();
    }

    private class Bool {
        boolean b;
    }


}
