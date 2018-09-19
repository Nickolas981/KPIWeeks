package com.dongumen.nickolas.kpiweeks


import android.app.Application
import com.dongumen.nickolas.kpiweeks.di.*
import org.koin.android.ext.android.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, apiModule, utilModule, repoModule, dataSourceModule, useCaseModule))
    }
}
