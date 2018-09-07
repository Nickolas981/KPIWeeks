package com.dongumen.nickolas.kpiweeks.utils

import com.dongumen.nickolas.kpiweeks.model.enteties.Day
import com.dongumen.nickolas.kpiweeks.model.enteties.Week
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class ResponseToScheduleUtil : KoinComponent {

    private val sharedPref: SharedPreferenceUtils by inject()
    private val dayInfo: DayInformationUtil by inject()
    val type = object : TypeToken<MutableList<Week>>() {}.type!!

    private fun parseWeek(js: JsonObject): Week {
        val week = Week()
        week.weekNumber = js.get("week_number").asString
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

    private fun refreshDates(list: MutableList<Week>) {
        for (week in list) {
            val dates = dayInfo.getDates(week.weekNumber.toInt() - 1)
            for (i in 0..5) {
                val day = week.days[i]
                day.dayName = day.dayName!!.split(" ".toRegex())[0]
                day.dayName += " " + dates[i]
            }
        }
    }

    fun parse(response: ResponseBody): MutableList<Week> {
        var list: MutableList<Week> = ArrayList()
        val str = response.string()
        if (!str.contains("data")) {
            list = parseFromSharedPreference(str)
            refreshDates(list)
        } else {
            val json = JsonParser().parse(str)
                    .asJsonObject
                    .getAsJsonObject("data")
                    .getAsJsonObject("weeks")

            list.add(parseWeek(json.getAsJsonObject("1")))
            list.add(parseWeek(json.getAsJsonObject("2")))

            writeIntoSharedPreference(list)
        }
        return list
    }

    fun parseFromSharedPreference(str: String) =
            GsonBuilder().create().fromJson<MutableList<Week>>(str, type)!!

    private fun writeIntoSharedPreference(list: MutableList<Week>) {
        sharedPref.setValue("json", GsonBuilder().create().toJson(list))
    }

    fun delete(week: Int, day: Int, lesson: Int) {
        val str = sharedPref.getStringValue("json", "")
        val res = parseFromSharedPreference(str)
        res[week].days[day].lessons!!.removeAt(lesson)
        writeIntoSharedPreference(res)
    }
}