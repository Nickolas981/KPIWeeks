package com.example.nickolas.kpiweeks.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nickolas.kpiweeks.R;
import com.example.nickolas.kpiweeks.fragments.WeekFragment;
import com.example.nickolas.kpiweeks.utils.DBController;
import com.example.nickolas.kpiweeks.utils.MyToolbar;
import com.example.nickolas.kpiweeks.utils.ResponseToSchedule;

public class ScheduleActivity extends AppCompatActivity {

    MyToolbar myToolbar;
    private Bool firstInit = new Bool();
    private Bool secondInit = new Bool();
    private FragmentTransaction fragT;
    String name, id;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        DBController.Companion.getInstance().initiate(this);
        myToolbar = new MyToolbar(findViewById(android.R.id.content));
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        myToolbar.getTitle().setText(name.toUpperCase());
        WeekFragment fragment1 = WeekFragment.newInstance(0, id);
        WeekFragment fragment2 = WeekFragment.newInstance(1, id);
        myToolbar.getSwitch().setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b)
                changeFragment(firstInit, fragment1);
            else
                changeFragment(secondInit, fragment2);
        });
        fragT = getFragmentManager().beginTransaction();
        changeFragment(firstInit, fragment1);
    }

    private void changeFragment(Bool init, android.app.Fragment fragment) {
       fragT = getFragmentManager().beginTransaction();
        if (!init.b) {
            fragT.add(R.id.frame, fragment).commit();
            init.b = true;
        } else {
            fragT.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    private class Bool {
        public boolean b;
    }


}
