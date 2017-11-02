package com.dongumen.nickolas.kpiweeks.di.component;

import com.dongumen.nickolas.kpiweeks.model.remote.IWeekDataSource;
import com.dongumen.nickolas.kpiweeks.model.remote.ISearchDataSource;

import retrofit2.Retrofit;

public interface ApiComponent {

    Retrofit retrofit();



//    ILogInDataSource logInDataSource();
    ISearchDataSource searchDataSource();
    IWeekDataSource weekDataSource();
}
