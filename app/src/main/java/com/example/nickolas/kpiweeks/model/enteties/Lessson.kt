package com.example.nickolas.kpiweeks.model.enteties

import com.google.gson.annotations.SerializedName

data class Lessson(

        @field:SerializedName("rooms")
        val rooms: List<RoomsItem?>? = null,

        @field:SerializedName("teachers")
        val teachers: List<TeachersItem?>? = null,

        @field:SerializedName("groups")
        val groups: List<GroupsItem?>? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("discipline")
        val discipline: Discipline? = null,

        @field:SerializedName("type")
        val type: Int? = null
)