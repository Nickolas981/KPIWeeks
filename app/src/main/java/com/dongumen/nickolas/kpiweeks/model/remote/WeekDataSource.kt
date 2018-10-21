package com.dongumen.nickolas.kpiweeks.model.remote

import com.dongumen.nickolas.kpiweeks.global.utils.SharedPreferenceUtils
import com.dongumen.nickolas.kpiweeks.model.enteties.Week
import com.dongumen.nickolas.kpiweeks.persistence.web.api.KpiApi
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class WeekDataSource(private val api: KpiApi) : IWeekDataSource, KoinComponent {

    private val sharedPref: SharedPreferenceUtils by inject()

    override fun getSchedule(group: String): List<Week> {
        return listOf()
//        return api.getTimetable(group)
    }

    private fun loadPreferences(): String =
            sharedPref.getStringValue("json", "")

}