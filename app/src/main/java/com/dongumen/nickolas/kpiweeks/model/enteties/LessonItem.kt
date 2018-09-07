package com.dongumen.nickolas.kpiweeks.model.enteties

data class LessonItem(
        val lessonNumber: Int,
        val lessonName: String,
        val last: Boolean,
        val isActive: Boolean,
        val teacherName: String,
        val lessonRoom: String,
        val lessonType: String,
        val dayName: String,
        val timeStart: String,
        val timeEnd: String
) : Item