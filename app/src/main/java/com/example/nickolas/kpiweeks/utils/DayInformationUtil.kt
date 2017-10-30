package com.example.nickolas.kpiweeks.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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

    fun getWeekNumber(): Int {
        val semester = getSemesterNumber()
        val calendar = Calendar.getInstance()
        val firstWeek: Int
        val currentWeek = calendar.get(Calendar.WEEK_OF_YEAR)
        if (semester == 1) {
            calendar.set(calendar.get(Calendar.YEAR), Calendar.SEPTEMBER, 0)
        } else if (semester == 2) {
            calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 0)
        }
        firstWeek = calendar.get(Calendar.WEEK_OF_YEAR)
        return (currentWeek - firstWeek) % 2 + 1
    }

    fun getDates(number: Int): List<String> {
        val firstWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)
        var n = number
        if (getWeekNumber() == 2) {
            if (number == 0) {
                n += 1
            } else {
                n = 0
            }
        }
        return getWeekDates(firstWeek + n)
    }


    @SuppressLint("SimpleDateFormat")
    private fun getWeekDates(week: Int): List<String> {
        val c = GregorianCalendar(Locale.getDefault())
        c.clear()
        c.set(Calendar.WEEK_OF_YEAR, week)
        c.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR))

        val list: MutableList<String> = ArrayList()


        for (i in 0 until 7) {
            c.set(Calendar.DAY_OF_WEEK, i)
            list.add(SimpleDateFormat("dd.MM").format(c.time))
        }

        list.add(list[0])
        list.removeAt(0)
        list.add(list[0])
        list.removeAt(0)

        return list
    }


    fun scrollTo(keys: MutableSet<String>): Int {
        var cal = getNormalDay(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))

        while (true) {
            if (cal == 7) return 8
            val d = keys.positionOf(cal.toString())
            if (d != keys.size) return d else cal += 1
        }
    }

    private fun getNormalDay(day: Int): Int = when (day) {
        0 -> 6
        1 -> 7
        else -> (day - 1)
    }

    private fun getSemesterNumber(): Int {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH) //9
        return when (currentMonth) {
            in 8..11 -> 1
            in 0..4 -> 2
            else -> 3
        }
    }

    private fun getTime(date: String, d : String): Long {
        val calendar = Calendar.getInstance()
        val list : MutableList<String> = date.split(":") as MutableList<String>
        list.addAll(d.split("."))
        calendar.set(Calendar.HOUR_OF_DAY, list[0].toInt())
        calendar.set(Calendar.MINUTE, list[1].toInt())
        calendar.set(Calendar.DAY_OF_MONTH, list[2].toInt())
        calendar.set(Calendar.MONTH, list[3].toInt() - 1)

        return calendar.timeInMillis
    }


    fun isActive(number: String, date : String): Boolean =
            (System.currentTimeMillis() >= getTime(getStartTime(number),date)
                    && System.currentTimeMillis() <= getTime(getFinishTime(number), date))

}

private fun <E> MutableSet<E>.positionOf(day: String): Int = this
        .takeWhile { it != day }
        .count()


