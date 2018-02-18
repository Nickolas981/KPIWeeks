package com.dongumen.nickolas.kpiweeks.model.enteties


class Week {

    var weekNumber = ""
    var days: MutableList<Day> = ArrayList()

    fun addDay(day : Day){
        days.add(day)
    }


    fun getAsList() : MutableList<Item>{
        val list : MutableList<Item> = ArrayList()

        for (day in days){
            list.add(HeaderItem(day.dayName!!))
            list.add(day)
        }
        
        return list
    }




}