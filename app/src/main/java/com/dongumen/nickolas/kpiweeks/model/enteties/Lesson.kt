package com.dongumen.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Lesson(
        @SerializedName("teacher_name") val teacherName: String,
        @SerializedName("rooms") val rooms: List<RoomsItem?>,
        @SerializedName("lesson_name") val lessonName: String,
        @SerializedName("time_start") val timeStart: String,
        @SerializedName("lesson_id") val lessonId: String,
        @SerializedName("lesson_week") val lessonWeek: String,
        @SerializedName("lesson_room") val lessonRoom: String,
        @SerializedName("day_name") val dayName: String,
        @SerializedName("group_id") val groupId: String,
        @SerializedName("lesson_type") val lessonType: String,
        @SerializedName("rate") val rate: String,
        @SerializedName("teachers") val teachers: List<TeachersItem>,
        @SerializedName("lesson_full_name") val lessonFullName: String,
        @SerializedName("day_number") val dayNumber: String,
        @SerializedName("time_end") val timeEnd: String,
        @SerializedName("lesson_number") val lessonNumber: String
) : Item {
    var last: Boolean = false
    var date: String = "" // day.dayName.replace("[^0-9.]".toRegex(), "")
}