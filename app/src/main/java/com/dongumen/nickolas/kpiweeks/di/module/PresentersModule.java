package com.dongumen.nickolas.kpiweeks.di.module;


import com.dongumen.nickolas.kpiweeks.di.scopes.Scope;
import com.dongumen.nickolas.kpiweeks.di.scopes.Scopes;
import com.dongumen.nickolas.kpiweeks.model.remote.ISearchDataSource;
import com.dongumen.nickolas.kpiweeks.model.remote.IWeekDataSource;
import com.dongumen.nickolas.kpiweeks.presenters.SearchPresenter;
import com.dongumen.nickolas.kpiweeks.presenters.WeekPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentersModule {



//    @Provides
//    @Scope(Scopes.VIEW)
//    public LogInPresenter provideLogInPresenter(ILogInDataSource logInDataSource) {
//        return new LogInPresenter(logInDataSource);
//    }
    @Provides
    @Scope(Scopes.VIEW)
    SearchPresenter provideLogInPresenter(ISearchDataSource searchDataSource) {
        return new SearchPresenter(searchDataSource);
    }
    @Provides
    @Scope(Scopes.VIEW)
    WeekPresenter provideWeekPresenter(IWeekDataSource weekDataSource) {
        return new WeekPresenter(weekDataSource);
    }
}
