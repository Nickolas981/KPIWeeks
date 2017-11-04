package com.dongumen.nickolas.kpiweeks.model.remote

import com.dongumen.nickolas.kpiweeks.App
import com.dongumen.nickolas.kpiweeks.api.KpiApi
import com.dongumen.nickolas.kpiweeks.utils.SharedPreferenceUtils
import okhttp3.MediaType
import okhttp3.ResponseBody
import rx.Observable
import javax.inject.Inject


class WeekDataSource(private val api: KpiApi) : IWeekDataSource {

    @Inject
    lateinit var sharedPref: SharedPreferenceUtils

    init {
        App.utilsComponent().inject(this)
    }

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