package com.dongumen.nickolas.kpiweeks.model.remote

import com.dongumen.nickolas.kpiweeks.model.enteties.Week


interface IWeekDataSource {
    fun getSchedule(group: String): List<Week>
}