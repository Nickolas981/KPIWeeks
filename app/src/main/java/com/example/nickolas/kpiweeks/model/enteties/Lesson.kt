package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Lesson(@field:SerializedName("discipline_name") val disciplineName: String? = null,
                  @field:SerializedName("rooms") val rooms: List<Int?>? = null,
                  @field:SerializedName("week") val week: Int? = null,
                  @field:SerializedName("teachers_short_names")
                  val teachersShortNames: List<String?>? = null,
                  @field:SerializedName("groups_names") val groupsNames: List<String?>? = null,
                  @field:SerializedName("rooms_full_names")
                  val roomsFullNames: List<String?>? = null,
                  @field:SerializedName("groups") val groups: List<Int?>? = null,
                  @field:SerializedName("discipline") val discipline: Int? = null,
                  @field:SerializedName("type") val type: Int? = null,
                  @field:SerializedName("number") val number: Int? = null,
                  @field:SerializedName("teachers") val teachers: List<Int?>? = null,
                  @field:SerializedName("id") val id: Int? = null,
                  @field:SerializedName("day") val day: Int? = null
)