package com.dongumen.nickolas.kpiweeks.views

import com.dongumen.nickolas.kpiweeks.model.enteties.Item


sealed class WeekEvents {
    data class ShowSchedule(val schedule: List<Item>) : WeekEvents()
}