package com.dongumen.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Day(
        @SerializedName("day_name") var dayName: String,
        @SerializedName("day_number") val dayNumber: Int,
        @SerializedName("lessons") val lessons: MutableList<Lesson>
)