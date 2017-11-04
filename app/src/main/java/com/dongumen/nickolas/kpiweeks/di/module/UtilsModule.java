package com.dongumen.nickolas.kpiweeks.di.module;

import android.content.Context;

import com.dongumen.nickolas.kpiweeks.utils.DayInformationUtil;
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil;
import com.dongumen.nickolas.kpiweeks.utils.SharedPreferenceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class UtilsModule {
    @Provides
    @Singleton
    SharedPreferenceUtils provideSharedPreferencesUtils(Context context) {
        return new SharedPreferenceUtils(context);
    }

    @Provides
    @Singleton
    DayInformationUtil provideDayInformationUtil() {
        return new DayInformationUtil();
    }

    @Provides
    @Singleton
    ResponseToScheduleUtil provideResponseToScheduleUtil() {
        return new ResponseToScheduleUtil();
    }
}
