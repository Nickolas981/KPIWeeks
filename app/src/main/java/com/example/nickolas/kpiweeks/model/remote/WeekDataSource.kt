package com.example.nickolas.kpiweeks.model.remote

import com.example.nickolas.kpiweeks.api.KpiApi
import com.example.nickolas.kpiweeks.utils.DBController
import okhttp3.MediaType
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by Nickolas on 16.10.2017.
 */
class WeekDataSource(val api: KpiApi) : IWeekDataSource {
    override fun getSchedule(group: String): Observable<ResponseBody> {
        val dataController = DBController.instance
        val string = dataController.read()
        if (string == "")
            return api.getTimetable(group)
        else
            return Observable.just(ResponseBody.create(MediaType.parse("text/plain"), string))
    }
}