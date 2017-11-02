package com.dongumen.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Day(

        @field:SerializedName("day_name")
        var dayName: String? = null,

        @field:SerializedName("day_number")
        val dayNumber: Int? = null,

        @field:SerializedName("lessons")
        val lessons: List<Lesson?>? = null
) : Item()