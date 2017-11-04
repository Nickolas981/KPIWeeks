package com.dongumen.nickolas.kpiweeks.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.dongumen.nickolas.kpiweeks.di.module.ApiModule;
import com.dongumen.nickolas.kpiweeks.di.module.AppModule;
import com.dongumen.nickolas.kpiweeks.di.module.UtilsModule;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        UtilsModule.class
})
public interface AppComponent extends ApiComponent {

    Context context();

    SharedPreferences preferences();

    Executor executor();

}
