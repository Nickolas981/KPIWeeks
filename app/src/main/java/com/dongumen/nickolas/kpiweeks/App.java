package com.dongumen.nickolas.kpiweeks;


import android.app.Application;

import com.dongumen.nickolas.kpiweeks.di.component.AppComponent;
import com.dongumen.nickolas.kpiweeks.di.component.DaggerAppComponent;
import com.dongumen.nickolas.kpiweeks.di.component.DaggerUtilsComponent;
import com.dongumen.nickolas.kpiweeks.di.component.UtilsComponent;
import com.dongumen.nickolas.kpiweeks.di.module.AppModule;

//import com.dongumen.nickolas.kpiweeks.di.component.UtilsComponent;

//import com.example.nickolas.kpiweeks.di.component.DaggerAppComponent;
//import com.example.nickolas.kpiweeks.di.component.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;
    private static UtilsComponent utilsComponent;

    public App() {
        super();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        utilsComponent = DaggerUtilsComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent appComponent() {
        return appComponent;
    }

    public static UtilsComponent utilsComponent() {
        return utilsComponent;
    }
}
