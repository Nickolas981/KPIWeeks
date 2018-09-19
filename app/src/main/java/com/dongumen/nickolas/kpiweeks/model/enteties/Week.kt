package com.dongumen.nickolas.kpiweeks.model.enteties


class Week {

    var weekNumber = ""
    var days: MutableList<Day> = ArrayList()

    fun addDay(day: Day) {
        days.add(day)
    }


    fun getAsList(): MutableList<Item> {
        val list: MutableList<Item> = ArrayList()
        days.forEach {
            list.add(HeaderItem(it.dayName))
            list.addAll(it.lessons)
        }
        return list
    }


}