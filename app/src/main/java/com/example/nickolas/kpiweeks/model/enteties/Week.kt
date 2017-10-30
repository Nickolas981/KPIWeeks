package com.example.nickolas.kpiweeks.model.enteties

import android.os.Build
import android.support.annotation.RequiresApi


class Week {
    var days: MutableMap<String, Day> = mutableMapOf()

    @RequiresApi(Build.VERSION_CODES.N)
    fun getList(): MutableList<Item> {
        val list: MutableList<Item> = ArrayList()

        val keys = days.keys

        for (key in keys) {
            list.add(HeaderItem(key))
            days[key]?.day = key
            days[key]?.let { list.add(it) }
        }

        return list
    }
}