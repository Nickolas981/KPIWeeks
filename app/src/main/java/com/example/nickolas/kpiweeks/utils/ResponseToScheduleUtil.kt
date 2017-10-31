package com.example.nickolas.kpiweeks.utils

import com.example.nickolas.kpiweeks.model.enteties.Day
import com.example.nickolas.kpiweeks.model.enteties.Week
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody


class ResponseToScheduleUtil {
    companion object {

        private fun parseWeek(js: JsonObject): Week {
            val week = Week()
            week.weekNumber = js.get("week_number").asString
            val dayInfo = DayInformationUtil()
            val dates = dayInfo.getDates(week.weekNumber.toInt() - 1)

            val json = js.getAsJsonObject("days")
            for (i in 1..6) {
                val day = Gson()
                        .fromJson<Day>(json.getAsJsonObject(i.toString()) as JsonElement,
                                Day::class.java)
                day.dayName += " " + dates[i - 1]
                week.addDay(day)
            }
            return week
        }

        fun parse(response: ResponseBody): MutableList<Week> {
            var list: MutableList<Week> = ArrayList()
            val type = object : TypeToken<MutableList<Week>>() {}.type
            val str = response.string()
            if (!str.contains("data"))
                list = GsonBuilder().create().fromJson(str, type)
            else {
                val json = JsonParser().parse(str)
                        .asJsonObject
                        .getAsJsonObject("data")
                        .getAsJsonObject("weeks")

                list.add(parseWeek(json.getAsJsonObject("1")))
                list.add(parseWeek(json.getAsJsonObject("2")))
            }
            return list
        }
    }
}