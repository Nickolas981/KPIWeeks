package com.example.nickolas.kpiweeks.model.remote

import com.example.nickolas.kpiweeks.api.KpiApi
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by Nickolas on 16.10.2017.
 */
class WeekDataSource(val api : KpiApi) : IWeekDataSource {
    override fun getSchedule(group: String): Observable<ResponseBody> = api.getTimetable(group)
}