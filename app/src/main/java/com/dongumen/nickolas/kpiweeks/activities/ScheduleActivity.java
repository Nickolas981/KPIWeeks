package com.dongumen.nickolas.kpiweeks.activities;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dongumen.nickolas.kpiweeks.App;
import com.dongumen.nickolas.kpiweeks.R;
import com.dongumen.nickolas.kpiweeks.fragments.WeekFragment;
import com.dongumen.nickolas.kpiweeks.utils.DayInformationUtil;
import com.dongumen.nickolas.kpiweeks.utils.SharedPreferenceUtils;
import com.dongumen.nickolas.kpiweeks.utils.WidgetUpdate;
import com.dongumen.nickolas.kpiweeks.widgets.FragmentChanger;
import com.dongumen.nickolas.kpiweeks.widgets.holders.MyToolbarHolder;

import java.util.Objects;

import javax.inject.Inject;

public class ScheduleActivity extends AppCompatActivity implements FragmentChanger {

    MyToolbarHolder myToolbar;
    private Bool firstInit = new Bool();
    private Bool secondInit = new Bool();
    WeekFragment fragment1;
    WeekFragment fragment2;
    String name;
    Fragment currentFragment;
    @Inject
    public SharedPreferenceUtils sharedPreferenceUtils;
    @Inject
    public DayInformationUtil dayInformationUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        App.utilsComponent().inject(this);
        myToolbar = new MyToolbarHolder(findViewById(android.R.id.content));
        myToolbar.getButtonText().setText("Вийти");
        myToolbar.getBackButton().setOnClickListener(view -> {
            firstInit.b = false;
            secondInit.b = false;
            getFragmentManager().beginTransaction()
                    .remove(getFragmentManager().findFragmentById(R.id.frame)).commit();
            deletePreferences();
            WidgetUpdate.Companion.updateWidget(getApplicationContext());
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
        fragment1 = WeekFragment.newInstance(0, name, this, dayInformationUtil);
        fragment2 = WeekFragment.newInstance(1, name, this, dayInformationUtil);
        myToolbar.getSwitch().setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b)
                changeFragment(firstInit, fragment1);
            else
                changeFragment(secondInit, fragment2);
        });
        int currentWeek = dayInformationUtil.getWeekNumber();
        if (currentWeek == 1) {
            changeFragment(firstInit, fragment1);
            myToolbar.getSwitch().setChecked(false);
            fragment1.scroll = true;
        } else {
            myToolbar.getSwitch().setChecked(true);
            changeFragment(secondInit, fragment2);
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

    private void changeFragment(Bool init, Fragment fragment) {
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
