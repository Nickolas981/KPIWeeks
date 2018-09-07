package com.dongumen.nickolas.kpiweeks.di.module

import android.content.Context
import com.dongumen.nickolas.kpiweeks.model.remote.ISearchDataSource
import com.dongumen.nickolas.kpiweeks.model.remote.IWeekDataSource
import com.dongumen.nickolas.kpiweeks.model.remote.SearchDataSource
import com.dongumen.nickolas.kpiweeks.model.remote.WeekDataSource
import com.dongumen.nickolas.kpiweeks.utils.DayInformationUtil
import com.dongumen.nickolas.kpiweeks.utils.ResponseToScheduleUtil
import com.dongumen.nickolas.kpiweeks.utils.SharedPreferenceUtils
import com.dongumen.nickolas.kpiweeks.web.BASE_URL
import com.dongumen.nickolas.kpiweeks.web.api.KpiApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.Executors

val apiModule = module {
    single {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(KpiApi::class.java)
    }
    single { SearchDataSource(get()) as ISearchDataSource }
    single { WeekDataSource(get()) as IWeekDataSource }
}

val appModule = module {
    single { Executors.newCachedThreadPool(); }
    single { androidContext().getSharedPreferences(androidContext().packageName, Context.MODE_PRIVATE) }
}

val utilModule = module {
    single { SharedPreferenceUtils(androidContext()) }
    single { DayInformationUtil() }
    single { ResponseToScheduleUtil() }
}

