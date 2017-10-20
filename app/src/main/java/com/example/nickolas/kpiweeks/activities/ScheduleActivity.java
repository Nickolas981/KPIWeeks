package com.example.nickolas.kpiweeks.activities;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nickolas.kpiweeks.R;
import com.example.nickolas.kpiweeks.fragments.WeekFragment;
import com.example.nickolas.kpiweeks.utils.DBController;
import com.example.nickolas.kpiweeks.utils.MyToolbar;

import java.util.Objects;

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
        loadText();
        if (Objects.equals(name, "") || Objects.equals(id, "")) {
            Intent intent = new Intent(this, GroupSearch.class);
            startActivityForResult(intent, 1);
        } else {
            startAction();
        }
    }


    private void startAction() {
        myToolbar.getTitle().setText(name.toUpperCase());
        WeekFragment fragment1 = WeekFragment.newInstance(0, id);
        WeekFragment fragment2 = WeekFragment.newInstance(1, id);
        myToolbar.getSwitch().setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b)
                changeFragment(firstInit, fragment1);
            else
                changeFragment(secondInit, fragment2);
        });
        changeFragment(firstInit, fragment1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            id = bundle.getString("id");
        }
        saveText();
        startAction();
        super.onActivityResult(requestCode, resultCode, data);
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

    @SuppressLint("ApplySharedPref")
    private void saveText() {
        SharedPreferences.Editor ed = getPreferences(Context.MODE_PRIVATE).edit();
        ed.putString("name", name);
        ed.putString("id", id);
        ed.commit();
    }

    private void loadText() {
        name = getPreferences(Context.MODE_PRIVATE).getString("name", "");
        id = getPreferences(Context.MODE_PRIVATE).getString("id", "");
    }

    private class Bool {
        public boolean b;
    }


}
