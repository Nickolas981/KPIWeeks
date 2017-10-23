package com.example.nickolas.kpiweeks.utils

import java.util.*




class DayInformationUtil {


    fun getStartTime(lessonId: String): String {
        return when (lessonId) {
            "1" -> "8:30"
            "2" -> "10:25"
            "3" -> "12:20"
            "4" -> "14:15"
            "5" -> "16:10"
            "6" -> "18:30"
            "7" -> "20:20"
            else -> "hz"
        }
    }

    fun getFinishTime(lessonId: String): String {
        return when (lessonId) {
            "1" -> "10:05"
            "2" -> "12:00"
            "3" -> "13:55"
            "4" -> "15:50"
            "5" -> "17:45"
            "6" -> "20:05"
            "7" -> "21:55"
            else -> "hz"
        }
    }

    fun getDay(dayId: String): String {
        return when (dayId) {
            "1" -> "Понеділок"
            "2" -> "Вівторок"
            "3" -> "Середа"
            "4" -> "Четвер"
            "5" -> "П'ятниця"
            "6" -> "Субота"
            else -> "hz"
        }
    }

    fun getWeekNumber() : Int {
        var semester = getSemesterNumber()
        var calendar = Calendar.getInstance()
        var firstWeek : Int
        var currentWeek = calendar.get(Calendar.WEEK_OF_YEAR)
        if (semester == 1){
            calendar.set(calendar.get(Calendar.YEAR), Calendar.SEPTEMBER, 0)
        } else if (semester == 2){
            calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 0)
        }
        firstWeek = calendar.get(Calendar.WEEK_OF_YEAR)
        return (currentWeek - firstWeek)% 2 + 1
    }


    fun getSemesterNumber() : Int{
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) //9
        return when (currentMonth){
            in 8..11 -> 1
            in 0..4 -> 2
            else -> 3
        }
    }
}
