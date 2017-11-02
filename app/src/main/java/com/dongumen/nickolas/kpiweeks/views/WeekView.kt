package com.dongumen.nickolas.kpiweeks.views

import com.dongumen.nickolas.kpiweeks.model.enteties.Week


interface WeekView : BaseView{
    fun showSchedule(week : Week)
}