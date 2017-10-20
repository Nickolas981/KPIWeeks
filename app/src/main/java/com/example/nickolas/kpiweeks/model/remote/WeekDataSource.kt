package com.example.nickolas.kpiweeks.model.remote

import com.example.nickolas.kpiweeks.api.KpiApi
import com.example.nickolas.kpiweeks.utils.DBController
import okhttp3.MediaType
import okhttp3.ResponseBody
import rx.Observable


class WeekDataSource(val api: KpiApi) : IWeekDataSource {
    override fun getSchedule(group: String): Observable<ResponseBody> {
        val dataController = DBController.instance
        dataController.start()
        val string = dataController.read()
        dataController.finish()
        if (string.equals(""))
            return api.getTimetable(group)
        else
            return Observable.just(ResponseBody.create(MediaType.parse("text/plain"), string))
    }
}