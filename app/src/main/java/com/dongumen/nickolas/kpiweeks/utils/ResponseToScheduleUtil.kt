package com.dongumen.nickolas.kpiweeks.utils

import com.dongumen.nickolas.kpiweeks.App
import com.dongumen.nickolas.kpiweeks.model.enteties.Day
import com.dongumen.nickolas.kpiweeks.model.enteties.Week
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import javax.inject.Inject


class ResponseToScheduleUtil {
    @Inject
    lateinit var sharedPref: SharedPreferenceUtils
    @Inject
    lateinit var dayInfo: DayInformationUtil
    val type = object : TypeToken<MutableList<Week>>() {}.type

    init {
        App.utilsComponent().inject(this)
    }

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

    fun parse(response: ResponseBody): MutableList<Week> {
        var list: MutableList<Week> = ArrayList()
        val str = response.string()
        if (!str.contains("data"))
            list = parseFromSharedPreference(str)
        else {
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

    fun writeIntoSharedPreference(list: MutableList<Week>) {
        sharedPref.setValue("json", GsonBuilder().create().toJson(list))
    }
}