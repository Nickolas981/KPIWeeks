package com.example.nickolas.kpiweeks.di.module;


import com.example.nickolas.kpiweeks.di.scopes.Scope;
import com.example.nickolas.kpiweeks.di.scopes.Scopes;
import com.example.nickolas.kpiweeks.model.remote.ISearchDataSource;
import com.example.nickolas.kpiweeks.model.remote.IWeekDataSource;
import com.example.nickolas.kpiweeks.presenters.SearchPresenter;
import com.example.nickolas.kpiweeks.presenters.WeekPresenter;

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
    public SearchPresenter provideLogInPresenter(ISearchDataSource searchDataSource) {
        return new SearchPresenter(searchDataSource);
    }
    @Provides
    @Scope(Scopes.VIEW)
    public WeekPresenter provideWeekPresenter(IWeekDataSource weekDataSource) {
        return new WeekPresenter(weekDataSource);
    }
}
