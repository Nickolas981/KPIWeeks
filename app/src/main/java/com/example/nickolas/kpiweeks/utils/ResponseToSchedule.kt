package com.example.nickolas.kpiweeks.utils

import com.example.nickolas.kpiweeks.model.enteties.Day
import com.example.nickolas.kpiweeks.model.enteties.Lesson
import com.example.nickolas.kpiweeks.model.enteties.Lessson
import com.example.nickolas.kpiweeks.model.enteties.Week
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import okhttp3.ResponseBody


class ResponseToSchedule {
    companion object {
        fun parse(response: ResponseBody): Week {
            val week = Week()
            val res = response.string()
            val parser = JsonParser()

            val weekJson = parser.parse(res)
                    .asJsonObject
                    .getAsJsonObject("data")
                    .getAsJsonObject("1")

            val dayKeys = weekJson.keySet()
            for (dayKey in dayKeys) {
                val dayJson = weekJson.getAsJsonObject(dayKey)
                val day = Day()
                val lessonKeys = dayJson.keySet()
                for (lessonKey : String in lessonKeys) {
                    val lesson  = Gson().fromJson<Lessson>(dayJson.get(lessonKey) as JsonElement,
                            Lessson::class.java)

                    day.lesssons[lessonKey] = lesson
                }
                week.days[dayKey] = day
            }
            return week
        }
    }
}