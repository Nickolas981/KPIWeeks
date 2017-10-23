package com.example.nickolas.kpiweeks;


import android.app.Application;

import com.example.nickolas.kpiweeks.di.component.AppComponent;
//import com.example.nickolas.kpiweeks.di.component.DaggerAppComponent;
//import com.example.nickolas.kpiweeks.di.component.DaggerAppComponent;
import com.example.nickolas.kpiweeks.di.component.DaggerAppComponent;
import com.example.nickolas.kpiweeks.di.module.ApiModule;
import com.example.nickolas.kpiweeks.di.module.AppModule;
import com.example.nickolas.kpiweeks.utils.SharedPreferenceUtils;

public class App extends Application {

    private AppComponent appComponent;

    public App() {
        super();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
