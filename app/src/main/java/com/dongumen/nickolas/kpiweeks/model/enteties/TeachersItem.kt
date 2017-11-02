package com.dongumen.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class TeachersItem(

	@field:SerializedName("teacher_full_name")
	val teacherFullName: String? = null,

	@field:SerializedName("teacher_name")
	val teacherName: String? = null,

	@field:SerializedName("teacher_url")
	val teacherUrl: String? = null,

	@field:SerializedName("teacher_id")
	val teacherId: String? = null,

	@field:SerializedName("teacher_rating")
	val teacherRating: String? = null,

	@field:SerializedName("teacher_short_name")
	val teacherShortName: String? = null
)