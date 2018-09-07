package com.dongumen.nickolas.kpiweeks.model.remote

import com.dongumen.nickolas.kpiweeks.utils.SharedPreferenceUtils
import com.dongumen.nickolas.kpiweeks.web.api.KpiApi
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import rx.Observable


class WeekDataSource(private val api: KpiApi) : IWeekDataSource, KoinComponent {

    private val sharedPref: SharedPreferenceUtils by inject()

    override fun getSchedule(group: String): Observable<ResponseBody> {
        val string = loadPreferences()
        return if (string == "")
            api.getTimetable(group)
        else
            Observable.just(ResponseBody.create(MediaType.parse("text/plain"), string))
    }

    private fun loadPreferences(): String =
            sharedPref.getStringValue("json", "")

}