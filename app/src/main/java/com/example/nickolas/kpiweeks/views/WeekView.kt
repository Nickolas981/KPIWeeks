package com.example.nickolas.kpiweeks.views

import com.example.nickolas.kpiweeks.model.enteties.Week


interface WeekView : BaseView{
    fun showSchedule(week : Week)
}