package com.example.nickolas.kpiweeks.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.nickolas.kpiweeks.R;
import com.example.nickolas.kpiweeks.fragments.WeekFragment;
import com.example.nickolas.kpiweeks.model.enteties.Group;
import com.example.nickolas.kpiweeks.model.enteties.Week;
import com.example.nickolas.kpiweeks.utils.MyToolbar;
import com.example.nickolas.kpiweeks.views.WeekView;

import org.jetbrains.annotations.NotNull;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        MyToolbar myToolbar = new MyToolbar(findViewById(android.R.id.content));
        WeekFragment fragment = new WeekFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

}
