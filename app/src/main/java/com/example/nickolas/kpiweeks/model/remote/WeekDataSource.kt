package com.example.nickolas.kpiweeks.model.remote

import android.app.Activity
import com.example.nickolas.kpiweeks.App
import com.example.nickolas.kpiweeks.activities.ScheduleActivity
import com.example.nickolas.kpiweeks.api.KpiApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import rx.Observable


class WeekDataSource(val api: KpiApi) : IWeekDataSource {
    override fun getSchedule(group: String, context: Activity): Observable<ResponseBody> {
//        val dataController = DBController.instance
//        dataController.start()
//        val string = dataController.read()
//        dataController.finish()

        val string = loadPreferences(context)


        if (string.equals(""))
            return api.getTimetable(group)
        else
            return Observable.just(ResponseBody.create(MediaType.parse("text/plain"), string))
    }

    private fun loadPreferences(ac: Activity): String =
            ScheduleActivity.sharedPreferenceUtils.getStringValue("json", "")

}