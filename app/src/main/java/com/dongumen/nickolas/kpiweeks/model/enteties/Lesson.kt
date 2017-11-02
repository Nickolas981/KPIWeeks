package com.dongumen.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Lesson(

	@field:SerializedName("teacher_name")
	val teacherName: String? = null,

	@field:SerializedName("rooms")
	val rooms: List<RoomsItem?>? = null,

	@field:SerializedName("lesson_name")
	val lessonName: String? = null,

	@field:SerializedName("time_start")
	val timeStart: String? = null,

	@field:SerializedName("lesson_id")
	val lessonId: String? = null,

	@field:SerializedName("lesson_week")
	val lessonWeek: String? = null,

	@field:SerializedName("lesson_room")
	val lessonRoom: String? = null,

	@field:SerializedName("day_name")
	val dayName: String? = null,

	@field:SerializedName("group_id")
	val groupId: String? = null,

	@field:SerializedName("lesson_type")
	val lessonType: String? = null,

	@field:SerializedName("rate")
	val rate: String? = null,

	@field:SerializedName("teachers")
	val teachers: List<TeachersItem?>? = null,

	@field:SerializedName("lesson_full_name")
	val lessonFullName: String? = null,

	@field:SerializedName("day_number")
	val dayNumber: String? = null,

	@field:SerializedName("time_end")
	val timeEnd: String? = null,

	@field:SerializedName("lesson_number")
	val lessonNumber: String? = null
)