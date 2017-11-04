package com.dongumen.nickolas.kpiweeks.di.component;

import com.dongumen.nickolas.kpiweeks.activities.ScheduleActivity;
import com.dongumen.nickolas.kpiweeks.di.module.AppModule;
import com.dongumen.nickolas.kpiweeks.di.module.UtilsModule;
import com.dongumen.nickolas.kpiweeks.model.remote.WeekDataSource;
import com.dongumen.nickolas.kpiweeks.presenters.WeekPresenter;
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil;
import com.dongumen.nickolas.kpiweeks.widgets.adapters.WeekAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {UtilsModule.class, AppModule.class}
)
public interface UtilsComponent {

    void inject(ScheduleActivity scheduleActivity);

    void inject(WeekDataSource weekDataSource);

    void inject(ResponseToScheduleUtil responseToScheduleUtil);

    void inject(WeekPresenter weekPresenter);

    void inject(WeekAdapter weekAdapter);
}
