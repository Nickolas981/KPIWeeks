package com.example.nickolas.kpiweeks.di.component;

import com.example.nickolas.kpiweeks.model.remote.IWeekDataSource;
import com.example.nickolas.kpiweeks.model.remote.ISearchDataSource;

import retrofit2.Retrofit;

public interface ApiComponent {

    Retrofit retrofit();



//    ILogInDataSource logInDataSource();
    ISearchDataSource searchDataSource();
    IWeekDataSource weekDataSource();
}
