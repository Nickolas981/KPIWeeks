package com.example.nickolas.kpiweeks.utils

import com.example.nickolas.kpiweeks.model.enteties.Day
import com.example.nickolas.kpiweeks.model.enteties.Week
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody


class ResponseToScheduleUtil {
    companion object {


//        fun parse(response: ResponseBody): MutableList<Week> {
//            var list: MutableList<Week> = ArrayList()
//            val typeOfHashMap = object : TypeToken<MutableList<Week>>() {}.type
//            val str = response.string()
//
//            if (!str.contains("data"))
//                list = GsonBuilder().create().fromJson(str, typeOfHashMap)
//            else {
//                val json = JsonParser().parse(str)
//                        .asJsonObject
//                        .getAsJsonObject("data")
//
//
//                list.add(parse_(json, 1))
//                list.add(parse_(json, 2))
//
//                val resultInJson = GsonBuilder().create().toJson(list)
//                ScheduleActivity.sharedPreferenceUtils.setValue("json", resultInJson)
//
//            }
//
//            return list
//        }

        //        private fun parse_(jsonObject: JsonObject, w: Int): Week {
//            val week = Week()
//
//            val weekJson = jsonObject.getAsJsonObject(w.toString())
//
//            val dayKeys = weekJson.keySet()
//            for (dayKey in dayKeys) {
//                val dayJson = weekJson.getAsJsonObject(dayKey)
//                val day = Day()
//                val lessonKeys = dayJson.keySet()
//                for (lessonKey: String in lessonKeys) {
//                    val lesson = Gson().fromJson<Lesson>(dayJson.get(lessonKey) as JsonElement,
//                            Lesson::class.java)
//
//                    day.lesssons[lessonKey] = lesson
//                }
//                week.days[dayKey] = day
//            }
//            return week
//        }

        private fun parseWeek(js: JsonObject): Week {
            val week = Week()

            week.weekNumber = js.get("week_number").asString
            val json = js.getAsJsonObject("days")
            for (i in 1..6) {
                week.addDay(Gson().fromJson<Day>
                ( json.getAsJsonObject(i.toString()) as JsonElement, Day::class.java))
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