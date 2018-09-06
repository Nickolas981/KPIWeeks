package com.dongumen.nickolas.kpiweeks.di.module

import com.dongumen.nickolas.kpiweeks.Constants
import com.dongumen.nickolas.kpiweeks.api.KpiApi
import com.dongumen.nickolas.kpiweeks.model.remote.SearchDataSource
import com.dongumen.nickolas.kpiweeks.model.remote.WeekDataSource
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val apiModule = module {
    single {
        Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiModule::class.java)
    }
    single { SearchDataSource(get()) }
    single { WeekDataSource(get()) }
}