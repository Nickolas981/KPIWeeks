package com.dongumen.nickolas.kpiweeks.views

import com.dongumen.nickolas.kpiweeks.global.presentation.BaseView
import com.dongumen.nickolas.kpiweeks.model.enteties.Item


interface WeekView : BaseView {
    fun showSchedule(schedule: List<Item>)
}