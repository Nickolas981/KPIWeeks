package com.example.nickolas.kpiweeks.model.enteties

import android.os.Build
import android.support.annotation.RequiresApi


class Week {

    var weekNumber = ""
    var days: MutableList<Day> = ArrayList()

    fun addDay(day : Day){
        days.add(day)
    }

}