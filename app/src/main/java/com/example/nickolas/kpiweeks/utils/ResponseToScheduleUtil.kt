package com.example.nickolas.kpiweeks.utils

import com.example.nickolas.kpiweeks.activities.ScheduleActivity
import com.example.nickolas.kpiweeks.model.enteties.Day
import com.example.nickolas.kpiweeks.model.enteties.Lesson
import com.example.nickolas.kpiweeks.model.enteties.Week
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody


class ResponseToScheduleUtil {
    companion object {

        private fun parse_(jsonObject: JsonObject, w: Int): Week {
            val week = Week()

            val weekJson = jsonObject.getAsJsonObject(w.toString())

            val dayKeys = weekJson.keySet()
            for (dayKey in dayKeys) {
                val dayJson = weekJson.getAsJsonObject(dayKey)
                val day = Day()
                val lessonKeys = dayJson.keySet()
                for (lessonKey: String in lessonKeys) {
                    val lesson = Gson().fromJson<Lesson>(dayJson.get(lessonKey) as JsonElement,
                            Lesson::class.java)

                    day.lesssons[lessonKey] = lesson
                }
                week.days[dayKey] = day
            }
            return week
        }

        fun parse(response: ResponseBody): MutableList<Week> {
            var list: MutableList<Week> = ArrayList()
            val typeOfHashMap = object : TypeToken<MutableList<Week>>() {}.type
            val str = response.string()

            if (!str.contains("data"))
                list = GsonBuilder().create().fromJson(str, typeOfHashMap)
            else {
                val json = JsonParser().parse(str)
                        .asJsonObject
                        .getAsJsonObject("data")


                list.add(parse_(json, 1))
                list.add(parse_(json, 2))

                val resultInJson = GsonBuilder().create().toJson(list)
                ScheduleActivity.sharedPreferenceUtils.setValue("json", resultInJson)

            }

            return list
        }

    }
}