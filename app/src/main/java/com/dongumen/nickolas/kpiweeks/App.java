package com.dongumen.nickolas.kpiweeks;


import android.app.Application;

import com.dongumen.nickolas.kpiweeks.di.component.AppComponent;
//import com.example.nickolas.kpiweeks.di.component.DaggerAppComponent;
//import com.example.nickolas.kpiweeks.di.component.DaggerAppComponent;
import com.dongumen.nickolas.kpiweeks.di.component.DaggerAppComponent;
import com.dongumen.nickolas.kpiweeks.di.module.ApiModule;
import com.dongumen.nickolas.kpiweeks.di.module.AppModule;

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
